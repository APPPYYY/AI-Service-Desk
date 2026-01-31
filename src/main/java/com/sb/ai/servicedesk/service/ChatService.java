package com.sb.ai.servicedesk.service;

import com.sb.ai.servicedesk.advisor.RagAdvisor;
import com.sb.ai.servicedesk.tools.TicketTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

        private final ChatClient chatClient;
        private final RagAdvisor ragAdvisor;
        private final ChatMemory chatMemory;
        private final TicketTool ticketTool;

        public ChatService(ChatClient.Builder chatClientBuilder, RagAdvisor ragAdvisor, ChatMemory chatMemory,
                        TicketTool ticketTool) {
                this.chatClient = chatClientBuilder.build();
                this.ragAdvisor = ragAdvisor;
                this.chatMemory = chatMemory;
                this.ticketTool = ticketTool;
        }

        public String chat(String conversationId, String message) {
                Prompt prompt = ragAdvisor.getPrompt(message);
                ChatResponse response = chatClient.prompt(prompt)
                                .functions(ticketTool.getCreateTicketFunction(), ticketTool.getTicketStatusFunction())
                                .call().chatResponse();
                chatMemory.add(conversationId, response.getResult().getOutput());
                return response.getResult().getOutput().getContent();
        }
}
