package org.github.alien11689.osgi.task.rest.impl;

import aQute.bnd.annotation.headers.RequireCapability;
import org.github.alien11689.osgi.task.repository.api.CreateTask;
import org.github.alien11689.osgi.task.repository.api.TaskRepository;
import org.github.alien11689.osgi.task.rest.impl.exception.TaskNotFound;
import org.github.alien11689.osgi.task.rest.impl.jaxb.Task;
import org.github.alien11689.osgi.task.rest.impl.jaxb.Tasks;
import org.ops4j.pax.cdi.api.OsgiService;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.stream.Collectors;

@Path("/")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Singleton
@RequireCapability(
        ns = "osgi.extender",
        filter = "(&(osgi.extender=osgi.blueprint)(version>=1.0)(!(version>=2.0)))")
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(@OsgiService TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GET
    public Tasks getTasks() {
        Tasks tasks = new Tasks();
        tasks.setTasks(taskRepository.fetchAll().stream().map(Task::fromRepo).collect(Collectors.toList()));
        return tasks;
    }

    @GET
    @Path(value = "{id}")
    public Task getTask(@PathParam("id") long id) throws Throwable {
        return taskRepository.fetchAll()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .map(Task::fromRepo)
                .orElseThrow(() -> {
                    throw new TaskNotFound();
                });
    }

    @POST
    public Response addTask(@QueryParam("name") String name, @QueryParam("description") String description, @Context UriInfo uriInfo) {
        long id = taskRepository.addTask(new CreateTask(name, description));
        return Response.created(uriInfo.getBaseUriBuilder().path(String.valueOf(id)).build()).build();
    }
}
