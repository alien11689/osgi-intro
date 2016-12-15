package org.github.alien11689.osgi.todo.spi;

import org.osgi.annotation.versioning.ConsumerType;

@ConsumerType
public interface CreateTask {
    String getName();

    String getDescription();
}
