package com.bht.ticketsystem.Repository;

import com.bht.ticketsystem.entity.db.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {
}
