package org.github.alien11689.osgi.task.persistent.impl

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import org.github.alien11689.osgi.task.repository.api.CreateTask
import org.github.alien11689.osgi.task.repository.api.RepositoryFailed
import org.github.alien11689.osgi.task.repository.api.Task
import org.github.alien11689.osgi.task.repository.api.TaskRepository
import org.ops4j.pax.cdi.api.OsgiService
import org.ops4j.pax.cdi.api.OsgiServiceProvider
import org.ops4j.pax.cdi.api.Properties
import org.ops4j.pax.cdi.api.Property

import javax.inject.Singleton
import javax.sql.DataSource
import java.sql.SQLException

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
        try {
            long id
            sql.withTransaction {
                List<GroovyRowResult> wrappedId = sql.executeInsert('''insert into tasks (id, name, description) values (nextval('tasks_ids'), ?, ?)''',
                        [createTask.name, createTask.description],
                        ['id'])
                id = wrappedId[0]['id'] as long
            }
            return id
        } catch (SQLException e) {
            throw new RepositoryFailed("Cannot persist task", e)
        }
    }

    @Override
    Collection<Task> fetchAll() {
        try {
            return sql.rows('select * from tasks').collect {
                new Task(
                        it['id'] as long,
                        it['name'] as String,
                        it['description'] as String
                ) as Task
            }
        } catch (SQLException e) {
            throw new RepositoryFailed("Cannot fetch tasks", e)
        }
    }
}