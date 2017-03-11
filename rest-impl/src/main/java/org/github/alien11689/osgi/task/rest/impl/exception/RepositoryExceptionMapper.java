package org.github.alien11689.osgi.task.rest.impl.exception;

import org.github.alien11689.osgi.task.repository.api.RepositoryFailed;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Singleton
public class RepositoryExceptionMapper implements ExceptionMapper<RepositoryFailed> {
    @Override
    public Response toResponse(RepositoryFailed repositoryFailed) {
        return Response
                .serverError()
                .entity(new TaskException(repositoryFailed.getMessage()))
                .build();
    }
}
