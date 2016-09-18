package ua.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.repository.MyOrderRepository;
import ua.service.MyOrderService;
import ua.shop_e.MyOrder;

@Service
public class MyOrderServiceImpl implements MyOrderService {

	@Autowired
	private  MyOrderRepository  myOrderRepository;

	@Override
	public void delete(int id) {
		myOrderRepository.delete(id);
		
	}

	@Override
	public List<MyOrder> findAll() {
		return myOrderRepository.findAll();
	}

	@Override
	@Transactional
	public void save(MyOrder form) {
		MyOrder myOrder = new MyOrder();
		myOrder.setUser(form.getUser());
		myOrder.setSize(form.getSize());
		myOrder.setId(form.getId());
		myOrder.setProduct(form.getProduct());
		myOrderRepository.save(myOrder);
	}

	@Override
	public MyOrder findOne(int id) {
		return myOrderRepository.findOne(id);
	}

}
