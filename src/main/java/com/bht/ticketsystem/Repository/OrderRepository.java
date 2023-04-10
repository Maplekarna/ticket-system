package com.bht.ticketsystem.Repository;

import com.bht.ticketsystem.entity.db.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
