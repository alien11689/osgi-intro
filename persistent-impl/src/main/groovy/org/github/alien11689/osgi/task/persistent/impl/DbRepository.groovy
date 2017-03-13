package org.github.alien11689.osgi.task.persistent.impl

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import org.apache.aries.blueprint.annotation.config.Config
import org.apache.aries.blueprint.annotation.config.ConfigProperty
import org.apache.aries.blueprint.annotation.config.DefaultProperty
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
        @Property(name = 'type', value = 'db'),
])
@Config(pid = 'org.github.alien11689.osgi.task.persistent.impl',
        defaults = [
                @DefaultProperty(key = 'error.cannotPersist', value = 'Cannot persist task'),
                @DefaultProperty(key = 'error.cannotFetch', value = 'Cannot fetch task'),
        ]
)
class DbRepository implements TaskRepository {

    private final Sql sql
    private final String cannotPersistMessage
    private final String cannotFetchMessage

    DbRepository(
            @OsgiService(filter = '(dataSourceName=taskDS)') DataSource dataSource,
            @ConfigProperty('${error.cannotPersist}') String cannotPersistMessage,
            @ConfigProperty('${error.cannotFetch}') String cannotFetchMessage
    ) {
        this.cannotFetchMessage = cannotFetchMessage
        this.cannotPersistMessage = cannotPersistMessage
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
            throw new RepositoryFailed(cannotPersistMessage, e)
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
            throw new RepositoryFailed(cannotFetchMessage, e)
        }
    }
}