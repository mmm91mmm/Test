package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.shop_e.Buy;

public interface BuyRepository extends JpaRepository<Buy, Integer> {

	
	@Query("SELECT p FROM Buy p LEFT JOIN FETCH p.orders")
	List<Buy> findAll();

	Buy findById(int id);
}
