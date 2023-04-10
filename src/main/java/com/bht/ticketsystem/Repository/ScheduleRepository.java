package com.bht.ticketsystem.Repository;

import com.bht.ticketsystem.entity.db.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
