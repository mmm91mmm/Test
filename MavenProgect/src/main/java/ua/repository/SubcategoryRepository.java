package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.shop_e.Category;
import ua.shop_e.Size;
import ua.shop_e.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer>, JpaSpecificationExecutor<Subcategory> {

	Subcategory findByName(String name);
	
	@Query("SELECT s FROM Subcategory s LEFT JOIN FETCH s.category WHERE s.id=:id")
	Subcategory findOne(@Param("id")int id);
	
	@Query("SELECT s FROM Subcategory s LEFT JOIN FETCH s.category")
	List<Subcategory> findAll();
	
	Subcategory findByCategory(Category category);
	
	@Query(value = "SELECT r FROM Subcategory r LEFT JOIN FETCH r.category",
			countQuery="SELECT count(r.id) FROM Subcategory r LEFT JOIN r.category")
	Page<Subcategory> findAll(Pageable pageable);
}
