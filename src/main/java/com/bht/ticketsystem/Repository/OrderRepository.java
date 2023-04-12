package com.bht.ticketsystem.Repository;

import com.bht.ticketsystem.entity.db.Order;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;



public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(value = "select * from orders where user_id = :userId", nativeQuery = true)
    Slice<Order> findByUserId(@Param("userId") String userId, Pageable pageable);
}
