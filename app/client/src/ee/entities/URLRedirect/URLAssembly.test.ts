import { APP_MODE } from "entities/App";
import urlBuilder, { EDITOR_TYPE } from "./URLAssembly";
import type { URLBuilderParams } from "./URLAssembly";
import { PLACEHOLDER_APP_SLUG, PLACEHOLDER_PAGE_SLUG } from "constants/routes";

describe("URLBuilder", () => {
  let originalLocation: typeof window.location;

  function mockPathname(pathname: string) {
    Object.defineProperty(window, "location", {
      value: {
        pathname,
        search: "",
      },
      writable: true,
    });
  }

  beforeEach(() => {
    // Store the original location object
    originalLocation = window.location;
  });

  afterEach(() => {
    // Restore the original location object after the test
    window.location = originalLocation;
  });

  it("should return the default editor type as pkg for package route", () => {
    mockPathname("/pkg/package-id/module-id");

    const editorType = urlBuilder.getDefaultEditorType();

    expect(editorType).toBe(EDITOR_TYPE.PKG);
  });

  it("should return the default editor type as app for app route", () => {
    mockPathname("/app/app-id/app-id");
    const editorType = urlBuilder.getDefaultEditorType();

    expect(editorType).toBe(EDITOR_TYPE.APP);
  });

  it("should return the default editor type as app for unknown route", () => {
    mockPathname("/unknown-editor/package-id/module-id");
    const editorType = urlBuilder.getDefaultEditorType();

    expect(editorType).toBe(EDITOR_TYPE.APP);
  });

  it("should generate the base path for PKG editor type", () => {
    mockPathname("/pkg/package-id/module-id/edit");

    const entityId = "someEntityId";
    const mode = APP_MODE.EDIT;

    const basePath = urlBuilder.generateBasePath(entityId, mode);

    expect(basePath).toBe(`/pkg/package-id/${entityId}/edit`);
  });

  it("should generate the base path for APP editor type", () => {
    mockPathname("/app/app-id/page-id/edit");

    const entityId = "someEntityId";
    const mode = APP_MODE.EDIT;
    const basePath = urlBuilder.generateBasePath(entityId, mode);

    expect(basePath).toBe(
      `/app/${PLACEHOLDER_APP_SLUG}/${PLACEHOLDER_PAGE_SLUG}-${entityId}/edit`,
    );
  });

  it("should resolve entity ID for PKG editor type", () => {
    mockPathname("/pkg/package-id/module-id/edit");

    const builderParams: URLBuilderParams = { moduleId: "someModuleId" };
    const entityIdInfo = urlBuilder.resolveEntityId(builderParams);

    expect(entityIdInfo.entityId).toBe("someModuleId");
    expect(entityIdInfo.entityType).toBe("moduleId");
  });

  it("should resolve entity ID for APP editor type", () => {
    mockPathname("/app/app-id/page-id/edit");

    const builderParams: URLBuilderParams = { pageId: "somePageId" };
    const entityIdInfo = urlBuilder.resolveEntityId(builderParams);

    expect(entityIdInfo.entityId).toBe("somePageId");
    expect(entityIdInfo.entityType).toBe("pageId");
  });

  it("should build a URL by checking the location", () => {
    mockPathname("/pkg/package-id/module-id/edit");

    const mode = APP_MODE.EDIT;
    const url = urlBuilder.build({}, mode);

    expect(url).toBe("/pkg/package-id/module-id/edit");
  });

  it("should build a URL by using the moduleId passed as builderParams", () => {
    mockPathname("/pkg/package-id/module-id/edit");

    const builderParams: URLBuilderParams = {
      moduleId: "someModuleId",
    };

    const mode = APP_MODE.EDIT;
    const url = urlBuilder.build(builderParams, mode);

    expect(url).toBe("/pkg/package-id/someModuleId/edit");
  });
});
