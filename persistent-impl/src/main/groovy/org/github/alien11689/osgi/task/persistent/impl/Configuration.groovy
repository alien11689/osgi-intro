package org.github.alien11689.osgi.task.persistent.impl

import org.osgi.service.metatype.annotations.AttributeDefinition
import org.osgi.service.metatype.annotations.ObjectClassDefinition

@ObjectClassDefinition(
        name = 'Config for task persistence impl',
        pid = ['org.github.alien11689.osgi.task.persistent.impl']
)
interface Configuration {

    @AttributeDefinition(defaultValue = ['Cannot persist task'])
    String error_cannotPersist()

    @AttributeDefinition(defaultValue = ['Cannot fetch task'])
    String error_cannotFetch()

}