package com.bht.ticketsystem.Repository;

import com.bht.ticketsystem.entity.db.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
