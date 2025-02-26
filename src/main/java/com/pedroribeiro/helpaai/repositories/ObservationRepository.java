package com.pedroribeiro.helpaai.repositories;

import com.pedroribeiro.helpaai.entities.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepository  extends JpaRepository<Observation,Integer> {
}
