import { createImmerReducer } from "utils/ReducerUtils";

import { ReduxActionTypes } from "@appsmith/constants/ReduxActionConstants";
import type { Package } from "@appsmith/constants/PackageConstants";
import type { ReduxAction } from "@appsmith/constants/ReduxActionConstants";
import type {
  FetchPackageResponse,
  FetchPackagesInWorkspaceResponse,
} from "@appsmith/api/PackageApi";

type ID = string;

export type PackagesReducerState = Record<ID, Package>;

const INITIAL_STATE: PackagesReducerState = {};

const packageReducer = createImmerReducer(INITIAL_STATE, {
  [ReduxActionTypes.CREATE_PACKAGE_FROM_WORKSPACE_SUCCESS]: (
    draftState: PackagesReducerState,
    action: ReduxAction<Package>,
  ) => {
    const { payload } = action;
    draftState[payload.id] = payload;

    return draftState;
  },

  [ReduxActionTypes.FETCH_PACKAGE_SUCCESS]: (
    draftState: PackagesReducerState,
    action: ReduxAction<FetchPackageResponse>,
  ) => {
    const { packageData } = action.payload;
    draftState[packageData.id] = packageData;

    return draftState;
  },

  [ReduxActionTypes.UPDATE_PACKAGE_SUCCESS]: (
    draftState: PackagesReducerState,
    action: ReduxAction<Package>,
  ) => {
    const packageData = action.payload;
    draftState[packageData.id] = packageData;

    return draftState;
  },

  [ReduxActionTypes.FETCH_ALL_PACKAGES_IN_WORKSPACE_SUCCESS]: (
    draftState: PackagesReducerState,
    action: ReduxAction<FetchPackagesInWorkspaceResponse>,
  ) => {
    draftState = {};
    const { packages } = action.payload;
    packages.map((pkg) => {
      draftState[pkg.id] = pkg;
    });

    return draftState;
  },
});

export default packageReducer;
