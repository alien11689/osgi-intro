package org.github.alien11689.osgi.task.rest.impl;

import aQute.bnd.annotation.headers.RequireCapability;
import org.github.alien11689.osgi.task.api.TaskRepository;
import org.github.alien11689.osgi.task.rest.impl.jaxb.Task;
import org.github.alien11689.osgi.task.rest.impl.jaxb.Tasks;
import org.ops4j.pax.cdi.api.OsgiService;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.stream.Collectors;

@Path("/")
@Produces("application/xml")
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
        tasks.setTasks(taskRepository.findAll().stream().map(Task::fromRepo).collect(Collectors.toList()));
        return tasks;
    }

    @POST
    public Task addTask(@QueryParam("name") String name, @QueryParam("description") String description) {
        Task task = new Task();
        task.setDescription(description);
        task.setName(name);
        long id = taskRepository.addTask(task);
        task.setId(id);
        return task;
    }
}
