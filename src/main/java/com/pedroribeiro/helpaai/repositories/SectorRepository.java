package com.pedroribeiro.helpaai.repositories;

import com.pedroribeiro.helpaai.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository  extends JpaRepository<Sector,Integer> {
}
