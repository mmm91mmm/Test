package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.shop_e.Colour;

public interface ColourRepository extends JpaRepository<Colour, Integer>, JpaSpecificationExecutor<Colour>  {

	Colour findByName(String name);

}
