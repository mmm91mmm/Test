package ua.service;

import java.util.List;

import ua.shop_e.Buy;

public interface BuyService {
	
	List <Buy> findAll();
	
	void save(Buy form);
	
	void delete(int id);

	Buy findById(int id);
}
