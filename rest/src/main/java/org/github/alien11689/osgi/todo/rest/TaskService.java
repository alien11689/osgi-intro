package org.github.alien11689.osgi.todo.rest;

import org.github.alien11689.osgi.todo.api.TaskRepository;
import org.github.alien11689.osgi.todo.rest.jaxb.Task;
import org.github.alien11689.osgi.todo.rest.jaxb.Tasks;
import org.github.alien11689.osgi.todo.spi.CreateTask;
import org.ops4j.pax.cdi.api.OsgiService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.xml.ws.Response;
import java.util.Collection;
import java.util.stream.Collectors;

@Path("/")
@Produces("application/xml")
@Singleton
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(@OsgiService TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GET
    public Tasks getTasks(){
        Tasks tasks = new Tasks();
        tasks.setTasks(taskRepository.findAll().stream().map(Task::fromRepo).collect(Collectors.toList()));
        return tasks;
    }

    @POST
    public Task addTask(@QueryParam("name") String name, @QueryParam("description") String description){
        Task task = new Task();
        task.setDescription(description);
        task.setName(name);
        long id = taskRepository.addTask(task);
        task.setId(id);
        return task;
    }
}
