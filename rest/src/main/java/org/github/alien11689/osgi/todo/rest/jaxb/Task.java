package org.github.alien11689.osgi.todo.rest.jaxb;

import lombok.Data;
import org.github.alien11689.osgi.todo.spi.CreateTask;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Task implements CreateTask {
       private  long id;
        private String name;
        private String description;

        public static Task fromRepo(org.github.alien11689.osgi.todo.api.Task src){
            Task task = new Task();
            task.setName(src.getName());
            task.setId(src.getId());
            task.setDescription(src.getDescription());
            return task;
        }
}
