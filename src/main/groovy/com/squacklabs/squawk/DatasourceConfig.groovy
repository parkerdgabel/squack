package com.squacklabs.squawk

import groovy.transform.CompileStatic
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.sqlite.SQLiteConfig
import org.sqlite.SQLiteConnection
import org.sqlite.SQLiteDataSource

import javax.inject.Inject
import javax.sql.DataSource
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

@CompileStatic
@Configuration
class DatasourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DatasourceConfig)

    @Value('${spring.datasource.url}')
    private String datasourceUrl

    @Value('${sqlite-vec.path}')
    private String sqliteVecPath

    private final ResourceLoader resourceLoader

    @Inject
    DatasourceConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader
    }

    @Bean
    DataSource dataSource() {
        SQLiteConfig config = new SQLiteConfig().with(true) {
            enableLoadExtension true
            setSharedCache true
        } as SQLiteConfig
        config.createConnection(datasourceUrl)
        SQLiteDataSource datasource = new SQLiteDataSource(config)
        try(Connection conn = datasource.connection) {
            if (conn instanceof SQLiteConnection) {
                SQLiteConnection sqliteConn = (SQLiteConnection) conn
                sqliteConn.database.enable_load_extension(true)
                try(Statement stmt = conn.createStatement()) {
                    stmt.execute("SELECT load_extension('" + resourceLoader.getResource(sqliteVecPath).getFile().getCanonicalPath() + "')")
                }
            } else {
                throw new SQLException("Cannot load extension; not an SQLite connection.")
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load SQLite extension", e)
        }
        datasource
    }

//    @PostConstruct
//    void loadSQLiteExtension() {
//        Resource resource = resourceLoader.getResource(sqliteVecPath)
//        try (Connection conn = dataSource.connection) {
//            if (conn instanceof SQLiteConnection) {
//                SQLiteConnection sqliteConn = (SQLiteConnection) conn;
//                sqliteConn.database.enable_load_extension(true)
//                try (Statement stmt = conn.createStatement()) {
//                    stmt.execute("SELECT load_extension('" + resource.file.absolutePath + "')")
//                }
//            } else {
//                throw new SQLException("Cannot load extension; not an SQLite connection.")
//            }
//        } catch (SQLException | IOException e) {
//            throw new RuntimeException("Failed to load SQLite extension", e)
//        }
//    }
}
