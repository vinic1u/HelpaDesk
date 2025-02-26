package com.pedroribeiro.helpaai.repositories;

import com.pedroribeiro.helpaai.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
