package com.example.demo.datasource

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MysqlDataSource {

    @Bean
    @ConfigurationProperties("app.datasource")
    fun HikariDataSourse(): HikariDataSource? {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource::class.java)
                .build()
    }

}