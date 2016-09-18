package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.shop_e.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Integer> {

}
