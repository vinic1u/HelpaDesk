package com.pedroribeiro.helpaai.repositories;

import com.pedroribeiro.helpaai.entities.Sector;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository  extends JpaRepository<Sector,Integer> {

    List<Sector> findByDeletedFalse();
}
