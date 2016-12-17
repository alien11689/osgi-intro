package org.github.alien11689.osgi.task.api;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface Task {

    long getId();

    String getName();

    String getDescription();
}
