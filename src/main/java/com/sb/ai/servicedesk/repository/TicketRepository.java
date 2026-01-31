package main.java.com.sb.ai.servicedesk.repository;

import main.java.com.sb.ai.servicedesk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
