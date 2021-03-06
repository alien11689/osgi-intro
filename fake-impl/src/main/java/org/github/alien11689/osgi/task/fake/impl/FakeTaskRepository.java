package org.github.alien11689.osgi.task.fake.impl;

import org.github.alien11689.osgi.task.repository.api.Task;
import org.github.alien11689.osgi.task.repository.api.TaskRepository;
import org.github.alien11689.osgi.task.repository.api.CreateTask;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Component(
        immediate = true,
        service = TaskRepository.class,
        property = {
                "type=fake",
                "service.ranking:Integer=" + Integer.MIN_VALUE
        })
public class FakeTaskRepository implements TaskRepository {
    private final AtomicLong counter = new AtomicLong();
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public long addTask(CreateTask createTask) {
        long id = counter.incrementAndGet();
        tasks.add(new Task(id, createTask.getName(), createTask.getDescription()));
        return id;
    }

    @Override
    public Collection<Task> fetchAll() {
        return tasks;
    }
}
