package com.sb.ai.servicedesk.tools;

import com.sb.ai.servicedesk.entity.Ticket;
import com.sb.ai.servicedesk.entity.TicketStatus;
import com.sb.ai.servicedesk.repository.TicketRepository;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.UUID;
import java.util.function.Function;

@Configuration
public class TicketTool {

    private final TicketRepository ticketRepository;

    public TicketTool(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Bean
    @Description("Create a new ticket with a given description and priority")
    public Function<Ticket, Ticket> createTicket() {
        return ticket -> {
            ticket.setId(UUID.randomUUID());
            ticket.setStatus(TicketStatus.OPEN);
            return ticketRepository.save(ticket);
        };
    }

    @Bean
    @Description("Get the status of a ticket by its ID")
    public Function<UUID, String> getTicketStatus() {
        return id -> ticketRepository.findById(id)
                .map(ticket -> ticket.getStatus().name())
                .orElse("Ticket not found");
    }
}
