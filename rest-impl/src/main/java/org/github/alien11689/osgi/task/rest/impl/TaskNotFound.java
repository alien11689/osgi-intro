package org.github.alien11689.osgi.task.rest.impl;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class TaskNotFound extends WebApplicationException {

    public TaskNotFound() {
        super(Response.Status.NOT_FOUND);
    }
}
