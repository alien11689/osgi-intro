package org.github.alien11689.osgi.task.rest.impl.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@NoArgsConstructor
public class TaskException {
    private String message;

    public TaskException(String message) {
        this.message = message;
    }
}
