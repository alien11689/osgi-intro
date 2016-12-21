package org.github.alien11689.osgi.task.persistent.impl

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import org.github.alien11689.osgi.task.api.Task
import org.github.alien11689.osgi.task.api.TaskRepository
import org.github.alien11689.osgi.task.spi.CreateTask
import org.ops4j.pax.cdi.api.OsgiService
import org.ops4j.pax.cdi.api.OsgiServiceProvider
import org.ops4j.pax.cdi.api.Properties
import org.ops4j.pax.cdi.api.Property

import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
@OsgiServiceProvider(classes = [TaskRepository])
@Properties([
        @Property(name = "type", value = "db"),
])
class DbRepository implements TaskRepository {

    private final Sql sql

    DbRepository(@OsgiService(filter = "(dataSourceName=taskDS)") DataSource dataSource) {
        this.sql = new Sql(dataSource)
    }

    @Override
    long addTask(CreateTask createTask) {
        long id
        sql.withTransaction {
            List<GroovyRowResult> wrappedId = sql.executeInsert('''insert into tasks (id, name, description) values (nextval('tasks_ids'), ?, ?)''',
                    [createTask.name, createTask.description],
                    ['id'])
            id = wrappedId[0]['id'] as long
        }
        return id
    }

    @Override
    Collection<Task> findAll() {
        return sql.rows('select * from tasks').collect {
            new InternalTask(
                    id: it['id'] as long,
                    name: it['name'] as String,
                    description: it['description'] as String
            ) as Task
        }
    }
}
