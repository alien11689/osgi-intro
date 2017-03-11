package org.github.alien11689.osgi.task.repository.api;

public class RepositoryFailed extends RuntimeException {
    public RepositoryFailed(String message, Throwable cause) {
        super(message, cause);
    }
}
