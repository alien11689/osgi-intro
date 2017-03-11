package org.github.alien11689.osgi.task.repository.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateTask {
    private final String name;

    private final String description;
}
