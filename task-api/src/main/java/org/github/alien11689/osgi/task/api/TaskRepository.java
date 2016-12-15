package org.github.alien11689.osgi.task.api;

import org.github.alien11689.osgi.task.spi.CreateTask;
import org.osgi.annotation.versioning.ProviderType;

import java.util.Collection;
import java.util.function.Predicate;

@ProviderType
public interface TaskRepository {

    long addTask(CreateTask createTask);

    Collection<Task> findAll();

}
