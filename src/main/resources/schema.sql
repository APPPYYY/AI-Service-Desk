CREATE TABLE vector_store (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    content TEXT NOT NULL,
    metadata JSON,
    embedding BLOB
);
