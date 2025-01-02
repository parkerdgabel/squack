package com.squacklabs.squawk

import groovy.transform.CompileStatic
import org.komamitsu.spring.data.sqlite.EnableSqliteRepositories
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableSqliteRepositories
@CompileStatic
class SquawkApplication {

    static void main(String[] args) {
        SpringApplication.run(SquawkApplication, args)
    }

}
