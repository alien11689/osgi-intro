package org.github.alien11689.osgi.task.persistent.impl

import org.apache.aries.blueprint.annotation.config.Config
import org.apache.aries.blueprint.annotation.config.ConfigProperty
import org.apache.commons.dbcp.BasicDataSource
import org.postgresql.Driver

import javax.enterprise.inject.Produces
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
@Config(pid = "org.github.alien11689.osgi.task.persistentimpl")
class DataSourceProvider {

    @Produces
    DataSource pgDataSource(
            @ConfigProperty('${db.url}') String url,
            @ConfigProperty('${db.user}') String user,
            @ConfigProperty('${db.password}') String password
    ) {
        new BasicDataSource(
                driverClassName: Driver.class.name,
                username: user,
                password: password,
                url: url
        )
    }
}
