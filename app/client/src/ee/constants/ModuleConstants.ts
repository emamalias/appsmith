export * from "ce/constants/ModuleConstants";
import type {
  Module as CE_Module,
  ModuleInputSection as CE_ModuleInputSection,
  ModuleInput as CE_ModuleInput,
} from "ce/constants/ModuleConstants";
import type { Action } from "entities/Action";

type ID = string;

export enum MODULE_TYPE {
  QUERY = "QUERY_MODULE",
  JS = "JS",
  UI = "UI",
}

export enum MODULE_PREFIX {
  QUERY = "Query",
}

export type ModuleAction = Action & {
  moduleId: string;
  packageId: string;
};
export interface ModuleInput extends CE_ModuleInput {
  label: string;
  defaultValue: string;
  controlType: string;
}

export interface ModuleInputSection extends CE_ModuleInputSection {
  sectionName: string;
  children: ModuleInput[];
}

export interface Module extends CE_Module {
  publicEntityId: ID;
  // list of settings enabled for module
  whitelistedPublicEntitySettingsForModule: string[];
  inputsForm: ModuleInputSection[];
  /**
   * list of settings enabled for module instance
   * for JSObject as public, value => ["confirmBeforeExecute", "executeOnLoad"]
   * for Actions/Queries as public, value would depend on plugin's settings
   */
  whitelistedPublicEntitySettingsForModuleInstance: string[];
  type: MODULE_TYPE;
  userPermissions: string[];
}
