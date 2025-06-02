package com.pedroribeiro.helpaai.repositories;

import com.pedroribeiro.helpaai.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository  extends JpaRepository<Ticket,Integer> {

    @Query(
            value = "SELECT t FROM Ticket t WHERE t.client.email = ?1"
    )
    List<Ticket> findClientTickets(String email);
}
