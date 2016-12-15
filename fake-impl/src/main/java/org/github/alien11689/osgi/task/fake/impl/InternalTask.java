package org.github.alien11689.osgi.task.fake.impl;

import lombok.Value;
import org.github.alien11689.osgi.task.api.Task;

@Value
public class InternalTask implements Task {

    long id;
    String name;
    String description;
}
