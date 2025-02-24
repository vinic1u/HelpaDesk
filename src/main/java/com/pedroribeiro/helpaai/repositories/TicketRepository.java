package com.pedroribeiro.helpaai.repositories;

import com.pedroribeiro.helpaai.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository  extends JpaRepository<Ticket,Integer> {
}
