package com.sb.ai.servicedesk.config;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.jdbc.JdbcVectorStore;
import org.springframework.ai.vectorstore.jdbc.MariaDbDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RagConfiguration {

    @Bean
    public VectorStore vectorStore(JdbcTemplate jdbcTemplate, EmbeddingClient embeddingClient) {
        return new JdbcVectorStore(jdbcTemplate, embeddingClient, new MariaDbDialect());
    }
}
