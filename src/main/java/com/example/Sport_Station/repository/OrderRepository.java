package com.example.Sport_Station.repository;

import com.example.Sport_Station.dto.projection.OrderView;
import com.example.Sport_Station.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders , Long> {

    @Query(value = "SELECT o.id, o.item_name, o.order_date, o.price, c.id AS customer_id, c.email, c.name FROM customer AS c JOIN orders AS o ON c.id = o.customer_id " +
            "WHERE (:customerName IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%',:customerName ,'%'))) AND ( :itemName IS NULL OR LOWER(o.item_name) LIKE LOWER(CONCAT('%' , :itemName , '%'))) ORDER BY c.name ASC LIMIT :size OFFSET :offset" , nativeQuery = true)
    List<OrderView>searchOrder(@Param("offset") Integer offset ,@Param("size") Integer size , @Param("customerName") String customerName , @Param("itemName") String itemName);

    @Query(value = "SELECT COUNT(*) FROM customer AS c JOIN orders AS o ON c.id = o.customer_id " +
            "WHERE (:customerName IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%',:customerName ,'%'))) " +
            "AND (:itemName IS NULL OR LOWER(o.item_name) LIKE LOWER(CONCAT('%', :itemName , '%')))", nativeQuery = true)
    Long countDataOrder(@Param("customerName") String customerName,
                        @Param("itemName") String itemName);
}
