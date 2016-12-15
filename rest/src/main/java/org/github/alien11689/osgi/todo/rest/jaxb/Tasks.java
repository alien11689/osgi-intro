package org.github.alien11689.osgi.todo.rest.jaxb;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Data
public class Tasks {
    private List<Task> tasks;
}
