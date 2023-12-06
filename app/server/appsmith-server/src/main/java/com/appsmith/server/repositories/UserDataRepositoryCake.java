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
public class UserDataRepositoryCake {
    private final UserDataRepository repository;

    // From CrudRepository
    public Mono<UserData> save(UserData entity) {
        return Mono.justOrEmpty(repository.save(entity));
    }

    public Flux<UserData> saveAll(Iterable<UserData> entities) {
        return Flux.fromIterable(repository.saveAll(entities));
    }

    public Mono<UserData> findById(String id) {
        return Mono.justOrEmpty(repository.findById(id));
    }
    // End from CrudRepository

    public UserData setUserPermissionsInObject(UserData obj, Set<String> permissionGroups) {
        return repository.setUserPermissionsInObject(obj, permissionGroups);
    }

    public Mono<UserData> findByUserId(String userId) {
        return Mono.justOrEmpty(repository.findByUserId(userId));
    }

    public Mono<UserData> archive(UserData entity) {
        return Mono.justOrEmpty(repository.archive(entity));
    }

    public Flux<UserData> findPhotoAssetsByUserIds(Iterable<String> userId) {
        return Flux.fromIterable(repository.findPhotoAssetsByUserIds(userId));
    }

    public Mono<Boolean> archiveAllById(java.util.Collection<String> ids) {
        return Mono.justOrEmpty(repository.archiveAllById(ids));
    }

    public Mono<UserData> retrieveById(String id) {
        return Mono.justOrEmpty(repository.retrieveById(id));
    }

    public boolean archiveById(String id) {
        return repository.archiveById(id);
    }

    public Flux<UserData> queryAll(
            List<Criteria> criterias, List<String> includeFields, AclPermission permission, Sort sort) {
        return Flux.fromIterable(repository.queryAll(criterias, includeFields, permission, sort));
    }

    public Mono<String> fetchMostRecentlyUsedWorkspaceId(String userId) {
        return Mono.justOrEmpty(repository.fetchMostRecentlyUsedWorkspaceId(userId));
    }

    public Flux<UserData> queryAll(List<Criteria> criterias, AclPermission permission) {
        return Flux.fromIterable(repository.queryAll(criterias, permission));
    }

    public UserData updateAndReturn(String id, Update updateObj, Optional<AclPermission> permission) {
        return repository.updateAndReturn(id, updateObj, permission);
    }

    public Mono<UpdateResult> saveReleaseNotesViewedVersion(String userId, String version) {
        return Mono.justOrEmpty(repository.saveReleaseNotesViewedVersion(userId, version));
    }

    public Flux<UserData> queryAll(List<Criteria> criterias, AclPermission permission, Sort sort) {
        return Flux.fromIterable(repository.queryAll(criterias, permission, sort));
    }

    public Mono<UpdateResult> removeIdFromRecentlyUsedList(
            String userId, String workspaceId, List<String> applicationIds) {
        return Mono.justOrEmpty(repository.removeIdFromRecentlyUsedList(userId, workspaceId, applicationIds));
    }

    public UserData setUserPermissionsInObject(UserData obj) {
        return repository.setUserPermissionsInObject(obj);
    }

    public Mono<UserData> findById(String id, AclPermission permission) {
        return Mono.justOrEmpty(repository.findById(id, permission));
    }
}
