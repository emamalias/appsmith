package com.appsmith.server.repositories;

import com.appsmith.external.models.*;
import com.appsmith.server.acl.AclPermission;
import com.appsmith.server.domains.*;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ApplicationRepositoryCake {
    private final ApplicationRepository repository;

    // From CrudRepository
    public Mono<Application> save(Application entity) {
        return Mono.justOrEmpty(repository.save(entity));
    }

    public Flux<Application> saveAll(Iterable<Application> entities) {
        return Flux.fromIterable(repository.saveAll(entities));
    }

    public Mono<Application> findById(String id) {
        return Mono.justOrEmpty(repository.findById(id));
    }
    // End from CrudRepository

    public Flux<Application> queryAll(List<Criteria> criterias, AclPermission permission, Sort sort) {
        return Flux.fromIterable(repository.queryAll(criterias, permission, sort));
    }

    public Mono<UpdateResult> updateFieldByDefaultIdAndBranchName(
            String defaultId,
            String defaultIdPath,
            Map<String, Object> fieldNameValueMap,
            String branchName,
            String branchNamePath,
            AclPermission permission) {
        return Mono.justOrEmpty(repository.updateFieldByDefaultIdAndBranchName(
                defaultId, defaultIdPath, fieldNameValueMap, branchName, branchNamePath, permission));
    }

    public Flux<Object> getAllApplicationIdsInWorkspaceAccessibleToARoleWithPermission(
            String workspaceId, AclPermission permission, String permissionGroupId) {
        return Flux.fromIterable(repository.getAllApplicationIdsInWorkspaceAccessibleToARoleWithPermission(
                workspaceId, permission, permissionGroupId));
    }

    public Mono<Long> countByNameAndWorkspaceId(String applicationName, String workspaceId, AclPermission permission) {
        return Mono.justOrEmpty(repository.countByNameAndWorkspaceId(applicationName, workspaceId, permission));
    }

    public Mono<Long> getAllApplicationsCountAccessibleToARoleWithPermission(
            AclPermission permission, String permissionGroupId) {
        return Mono.justOrEmpty(
                repository.getAllApplicationsCountAccessibleToARoleWithPermission(permission, permissionGroupId));
    }

    public Mono<Long> countByDeletedAtNull() {
        return Mono.justOrEmpty(repository.countByDeletedAtNull());
    }

    public Mono<UpdateResult> setPages(String applicationId, List<ApplicationPage> pages) {
        return Mono.justOrEmpty(repository.setPages(applicationId, pages));
    }

    public Flux<Application> queryAll(List<Criteria> criterias, AclPermission permission) {
        return Flux.fromIterable(repository.queryAll(criterias, permission));
    }

    public Mono<Application> findByIdAndExportWithConfiguration(String id, boolean exportWithConfiguration) {
        return Mono.justOrEmpty(repository.findByIdAndExportWithConfiguration(id, exportWithConfiguration));
    }

    public Flux<Application> findByClonedFromApplicationId(String applicationId, AclPermission permission) {
        return Flux.fromIterable(repository.findByClonedFromApplicationId(applicationId, permission));
    }

    public Mono<Application> retrieveById(String id) {
        return Mono.justOrEmpty(repository.retrieveById(id));
    }

    public Mono<Application> archive(Application entity) {
        return Mono.justOrEmpty(repository.archive(entity));
    }

    public Application updateAndReturn(String id, Update updateObj, Optional<AclPermission> permission) {
        return repository.updateAndReturn(id, updateObj, permission);
    }

    public Mono<UpdateResult> setGitAuth(String applicationId, GitAuth gitAuth, AclPermission aclPermission) {
        return Mono.justOrEmpty(repository.setGitAuth(applicationId, gitAuth, aclPermission));
    }

    public Flux<Application> findByWorkspaceId(String workspaceId, AclPermission permission) {
        return Flux.fromIterable(repository.findByWorkspaceId(workspaceId, permission));
    }

    public Flux<Application> getApplicationByGitDefaultApplicationId(
            String defaultApplicationId, AclPermission permission) {
        return Flux.fromIterable(repository.getApplicationByGitDefaultApplicationId(defaultApplicationId, permission));
    }

    public Mono<UpdateResult> addPageToApplication(
            String applicationId, String pageId, boolean isDefault, String defaultPageId) {
        return Mono.justOrEmpty(repository.addPageToApplication(applicationId, pageId, isDefault, defaultPageId));
    }

    public Mono<UpdateResult> protectBranchedApplications(
            String applicationId, List<String> branchNames, AclPermission permission) {
        return Mono.justOrEmpty(repository.protectBranchedApplications(applicationId, branchNames, permission));
    }

    public Mono<UpdateResult> unprotectAllBranches(String applicationId, AclPermission permission) {
        return Mono.justOrEmpty(repository.unprotectAllBranches(applicationId, permission));
    }

    public Flux<Application> findByMultipleWorkspaceIds(Set<String> workspaceIds, AclPermission permission) {
        return Flux.fromIterable(repository.findByMultipleWorkspaceIds(workspaceIds, permission));
    }

    public Application setUserPermissionsInObject(Application obj) {
        return repository.setUserPermissionsInObject(obj);
    }

    public Application setUserPermissionsInObject(Application obj, Set<String> permissionGroups) {
        return repository.setUserPermissionsInObject(obj, permissionGroups);
    }

    public Mono<Application> getApplicationByDefaultApplicationIdAndDefaultBranch(String defaultApplicationId) {
        return Mono.justOrEmpty(repository.getApplicationByDefaultApplicationIdAndDefaultBranch(defaultApplicationId));
    }

    public Mono<Application> getApplicationByGitBranchAndDefaultApplicationId(
            String defaultApplicationId, String branchName, AclPermission aclPermission) {
        return Mono.justOrEmpty(repository.getApplicationByGitBranchAndDefaultApplicationId(
                defaultApplicationId, branchName, aclPermission));
    }

    public Mono<Boolean> archiveAllById(java.util.Collection<String> ids) {
        return Mono.justOrEmpty(repository.archiveAllById(ids));
    }

    public Mono<Application> getApplicationByGitBranchAndDefaultApplicationId(
            String defaultApplicationId,
            List<String> projectionFieldNames,
            String branchName,
            AclPermission aclPermission) {
        return Mono.justOrEmpty(repository.getApplicationByGitBranchAndDefaultApplicationId(
                defaultApplicationId, projectionFieldNames, branchName, aclPermission));
    }

    public boolean archiveById(String id) {
        return repository.archiveById(id);
    }

    public Mono<Long> getGitConnectedApplicationWithPrivateRepoCount(String workspaceId) {
        return Mono.justOrEmpty(repository.getGitConnectedApplicationWithPrivateRepoCount(workspaceId));
    }

    public Mono<Application> findByIdAndWorkspaceId(String id, String workspaceId, AclPermission permission) {
        return Mono.justOrEmpty(repository.findByIdAndWorkspaceId(id, workspaceId, permission));
    }

    public Flux<Application> findAllUserApps(AclPermission permission) {
        return Flux.fromIterable(repository.findAllUserApps(permission));
    }

    public Mono<Application> findByName(String name, AclPermission permission) {
        return Mono.justOrEmpty(repository.findByName(name, permission));
    }

    public Mono<Application> getApplicationByGitBranchAndDefaultApplicationId(
            String defaultApplicationId, String branchName, Optional<AclPermission> permission) {
        return Mono.justOrEmpty(repository.getApplicationByGitBranchAndDefaultApplicationId(
                defaultApplicationId, branchName, permission));
    }

    public Mono<UpdateResult> setDefaultPage(String applicationId, String pageId) {
        return Mono.justOrEmpty(repository.setDefaultPage(applicationId, pageId));
    }

    public Flux<Application> findByIdIn(List<String> ids) {
        return Flux.fromIterable(repository.findByIdIn(ids));
    }

    public Flux<Application> findByWorkspaceId(String workspaceId) {
        return Flux.fromIterable(repository.findByWorkspaceId(workspaceId));
    }

    public Mono<UpdateResult> setAppTheme(
            String applicationId, String editModeThemeId, String publishedModeThemeId, AclPermission aclPermission) {
        return Mono.justOrEmpty(
                repository.setAppTheme(applicationId, editModeThemeId, publishedModeThemeId, aclPermission));
    }

    public Flux<Application> getGitConnectedApplicationByWorkspaceId(String workspaceId) {
        return Flux.fromIterable(repository.getGitConnectedApplicationByWorkspaceId(workspaceId));
    }

    public Mono<Application> findById(String id, AclPermission permission) {
        return Mono.justOrEmpty(repository.findById(id, permission));
    }

    public Mono<Long> countByWorkspaceId(String workspaceId) {
        return Mono.justOrEmpty(repository.countByWorkspaceId(workspaceId));
    }

    public Flux<String> getAllApplicationId(String workspaceId) {
        return Flux.fromIterable(repository.getAllApplicationId(workspaceId));
    }

    public Flux<Application> findByClonedFromApplicationId(String clonedFromApplicationId) {
        return Flux.fromIterable(repository.findByClonedFromApplicationId(clonedFromApplicationId));
    }

    public Flux<Application> queryAll(
            List<Criteria> criterias, List<String> includeFields, AclPermission permission, Sort sort) {
        return Flux.fromIterable(repository.queryAll(criterias, includeFields, permission, sort));
    }
}
