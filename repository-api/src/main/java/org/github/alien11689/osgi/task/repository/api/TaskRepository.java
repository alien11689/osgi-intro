package org.github.alien11689.osgi.task.repository.api;

import org.osgi.annotation.versioning.ProviderType;

import java.util.Collection;

@ProviderType
public interface TaskRepository {

    long addTask(CreateTask createTask) throws RepositoryFailed;

    Collection<Task> fetchAll() throws RepositoryFailed;

}
