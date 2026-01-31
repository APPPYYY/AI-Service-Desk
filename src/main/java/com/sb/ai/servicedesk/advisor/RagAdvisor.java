package com.sb.ai.servicedesk.advisor;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Description("AI Service Desk Assistant")
public class RagAdvisor {

    private final VectorStore vectorStore;

    public RagAdvisor(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public Prompt getPrompt(String message) {
        List<Document> similarDocuments = this.vectorStore.similaritySearch(SearchRequest.query(message).withTopK(2));
        String documents = similarDocuments.stream().map(Document::getContent).collect(Collectors.joining("\n"));
        var systemPromptTemplate = new SystemPromptTemplate(
                """
                        You are an AI Service Desk Assistant.
                        Use the following information to answer the user's question:
                        {documents}
                        """);
        return new Prompt(List.of(systemPromptTemplate.createMessage(Map.of("documents", documents))));
    }
}
