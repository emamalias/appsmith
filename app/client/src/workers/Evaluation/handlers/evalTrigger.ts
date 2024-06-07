import { dataTreeEvaluator } from "./evalTree";
import type { EvalTreeResponseData, EvalWorkerASyncRequest } from "../types";
import ExecutionMetaData from "../fns/utils/ExecutionMetaData";
import { makeEntityConfigsAsObjProperties } from "@appsmith/workers/Evaluation/dataTreeUtils";
import {
  generateOptimisedUpdatesAndSetPrevState,
  uniqueOrderUpdatePaths,
} from "../helpers";
import type { UpdateDataTreeMessageData } from "sagas/EvalWorkerActionSagas";
import { MessageType, sendMessage } from "utils/MessageUtil";
import { MAIN_THREAD_ACTION } from "@appsmith/workers/Evaluation/evalWorkerActions";

export default async function (request: EvalWorkerASyncRequest) {
  const { data } = request;
  const {
    callbackData,
    dynamicTrigger,
    eventType,
    globalContext,
    triggerMeta,
    unEvalTree,
  } = data;
  if (!dataTreeEvaluator) {
    return { triggers: [], errors: [] };
  }

  ExecutionMetaData.setExecutionMetaData({ triggerMeta, eventType });

  if (!triggerMeta.onPageLoad) {
    const { evalOrder, unEvalUpdates } = dataTreeEvaluator.setupUpdateTree(
      unEvalTree.unEvalTree,
      unEvalTree.configTree,
      undefined,
      //TODO: the evalTrigger can be optimised to not diff all JS actions
      { isAllAffected: true, ids: [] },
    );

    const updateResponse = dataTreeEvaluator.evalAndValidateSubTree(
      evalOrder,
      unEvalTree.configTree,
      unEvalUpdates,
    );
    const dataTree = makeEntityConfigsAsObjProperties(
      dataTreeEvaluator.evalTree,
      {
        evalProps: dataTreeEvaluator.evalProps,
      },
    );

    /** Make sure evalMetaUpdates is sanitized to prevent postMessage failure */
    const evalMetaUpdates = JSON.parse(
      JSON.stringify(updateResponse.evalMetaUpdates),
    );

    const staleMetaIds = updateResponse.staleMetaIds;
    const unevalTree = dataTreeEvaluator.getOldUnevalTree();

    const allUnevalUpdates = unEvalUpdates.map(
      (update) => update.payload.propertyPath,
    );
    const completeEvalOrder = uniqueOrderUpdatePaths([
      ...allUnevalUpdates,
      ...evalOrder,
    ]);

    const updates = generateOptimisedUpdatesAndSetPrevState(
      dataTree,
      dataTreeEvaluator,
      completeEvalOrder,
    );
    const evalTreeResponse: EvalTreeResponseData = {
      updates,
      dependencies: {},
      errors: [],
      evalMetaUpdates,
      evaluationOrder: evalOrder,
      jsUpdates: {},
      logs: [],
      unEvalUpdates,
      isCreateFirstTree: false,
      staleMetaIds,
      removedPaths: [],
      isNewWidgetAdded: false,
      undefinedEvalValuesMap: dataTreeEvaluator?.undefinedEvalValuesMap || {},
      jsVarsCreatedEvent: [],
    };

    const data: UpdateDataTreeMessageData = {
      workerResponse: evalTreeResponse,
      unevalTree,
    };

    sendMessage.call(self, {
      messageType: MessageType.DEFAULT,
      body: {
        data,
        method: MAIN_THREAD_ACTION.UPDATE_DATATREE,
      },
    });
  }
  const res = dataTreeEvaluator.evaluateTriggers(
    dynamicTrigger,
    dataTreeEvaluator.getEvalTree(),
    unEvalTree.configTree,
    callbackData,
    {
      globalContext,
      eventType,
      triggerMeta,
    },
  );

  return res;
}
