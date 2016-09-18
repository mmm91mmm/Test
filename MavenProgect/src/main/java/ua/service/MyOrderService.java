package ua.service;

import java.util.List;

import ua.shop_e.MyOrder;

public interface MyOrderService {
	
	void delete(int id);
	
	List<MyOrder> findAll();

//	void save(String userName, String productName, String sizeName, String colourName);

	void save(MyOrder form);

	MyOrder findOne(int id);

}
