package org.github.alien11689.osgi.task.rest.impl.jaxb;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Data
public class Tasks {
    private List<Task> tasks;
}
