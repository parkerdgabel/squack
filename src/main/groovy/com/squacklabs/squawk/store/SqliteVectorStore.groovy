package com.squacklabs.squawk.store

import groovy.transform.CompileStatic
import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.stereotype.Component

import javax.inject.Inject
import javax.sql.DataSource

@CompileStatic
class SqliteVectorStore implements VectorStore {

    private final DataSource dataSource
    private final String tableName

    @Inject
    SqliteVectorStore(DataSource dataSource, String tableName) {
        this.dataSource = dataSource
        this.tableName = tableName
    }

    @Override
    void add(List<Document> documents) {
        def conn = dataSource.connection
        def res = conn.createStatement().with { stmt ->
            documents.each { doc ->
                stmt.addBatch("INSERT INTO ${tableName} (id, vector, content) VALUES ('${doc.id}', '${doc.embedding}', '${doc.content}')")
            }
            stmt.executeBatch()
        }
    }

    @Override
    Optional<Boolean> delete(List<String> idList) {
        def conn = dataSource.connection
        def res = conn.createStatement().with { stmt ->
            idList.each { id ->
                stmt.addBatch("DELETE FROM ${tableName} WHERE id = '${id}'")
            }
            stmt.executeBatch()
        }
    }

    @Override
    List<Document> similaritySearch(SearchRequest request) {

    }
}
