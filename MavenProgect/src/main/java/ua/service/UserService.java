package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.shop_e.Subcategory;
import ua.shop_e.User;

public interface UserService {
	
	User findByMail(String mail);
	
	void delete(int id);
	
	List<User> findAll();

//	void save(String login, String mail, String password, String roleName);

	User findOneUser(int id);

	void save(User form);
	
	User findByLogin(String login);

	Page<User> findAll(Pageable pageable);

}
