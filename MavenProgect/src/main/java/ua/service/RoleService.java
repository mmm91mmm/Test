package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.filterForm.RoleFilterForm;
import ua.shop_e.Product;
import ua.shop_e.Role;

public interface RoleService {

	Role findByName(String name);
	
	void delete(int id);
	
	List<Role> findAll();

	Role findOne(int id);

	void save(Role role);

	Page<Role> findAll(Pageable pageable, RoleFilterForm form);

}
