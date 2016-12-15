package org.github.alien11689.osgi.todo.api;

import org.github.alien11689.osgi.todo.spi.CreateTask;
import org.osgi.annotation.versioning.ProviderType;

import java.util.Collection;
import java.util.function.Predicate;

@ProviderType
public interface TaskRepository {

    long addTask(CreateTask createTask);

    Collection<Task> findAll();

}
