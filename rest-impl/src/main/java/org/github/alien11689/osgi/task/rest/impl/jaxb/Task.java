package org.github.alien11689.osgi.task.rest.impl.jaxb;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Task {
    private long id;
    private String name;
    private String description;

    public static Task fromRepo(org.github.alien11689.osgi.task.repository.api.Task src) {
        Task task = new Task();
        task.setName(src.getName());
        task.setId(src.getId());
        task.setDescription(src.getDescription());
        return task;
    }
}
