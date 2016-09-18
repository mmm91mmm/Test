package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.shop_e.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	Product findByName(String name);

	@Query("SELECT p FROM Product p LEFT JOIN FETCH p.manufacturer LEFT JOIN FETCH p.country LEFT JOIN FETCH p.subcategory LEFT JOIN FETCH p.colour where p.id=:id")
	Product findById(@Param("id") int id);
	
	@Query("SELECT distinct p FROM Product p LEFT JOIN FETCH p.manufacturer LEFT JOIN FETCH p.country LEFT JOIN FETCH p.subcategory LEFT JOIN FETCH p.colour LEFT JOIN FETCH p.sizes")
	List<Product> findAllInited();

	
	@Query("SELECT distinct p FROM Product p LEFT JOIN FETCH p.sizes where p.id=:id")
	Product findAllSizes(@Param("id") int id);

	
	@Query(value = "SELECT distinct p FROM Product p LEFT JOIN FETCH p.manufacturer LEFT JOIN FETCH p.country LEFT JOIN FETCH p.subcategory LEFT JOIN FETCH p.colour LEFT JOIN FETCH p.sizes",
		countQuery="SELECT distinct count(p.id) FROM Product p LEFT JOIN p.manufacturer LEFT JOIN p.country LEFT JOIN p.subcategory LEFT JOIN p.colour LEFT JOIN p.sizes")
	Page<Product> findAll(Pageable pageable);
}
//JOIN FETCH p.sizes
//distinct