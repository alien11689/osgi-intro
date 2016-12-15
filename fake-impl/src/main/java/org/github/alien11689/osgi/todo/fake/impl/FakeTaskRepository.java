package org.github.alien11689.osgi.todo.fake.impl;

import org.github.alien11689.osgi.todo.api.Task;
import org.github.alien11689.osgi.todo.api.TaskRepository;
import org.github.alien11689.osgi.todo.spi.CreateTask;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
@OsgiServiceProvider(classes = TaskRepository.class)
@Properties({
        @Property(name = "type", value = "fake"),
        @Property(name = "service.ranking", value = "1"),
})
public class FakeTaskRepository implements TaskRepository{
    private final AtomicLong counter = new AtomicLong();
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public long addTask(CreateTask createTask) {
        long id = counter.incrementAndGet();
        tasks.add(new InternalTask(id, createTask.getName(), createTask.getDescription()));
        return id;
    }

    @Override
    public Collection<Task> findAll() {
        return tasks;
    }
}
