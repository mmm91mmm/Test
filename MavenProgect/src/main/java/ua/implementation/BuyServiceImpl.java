package ua.implementation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ua.repository.BuyRepository;
import ua.service.BuyService;
import ua.shop_e.Buy;

@Service
public class BuyServiceImpl implements BuyService {

	@Resource
	private  BuyRepository  buyRepository;
	
	@Override
	public List<Buy> findAll() {
		return buyRepository.findAll();
	}

	@Override
	public void save(Buy form) {
		Buy buy = new Buy();
		buy.setId(form.getId());
		buy.setOrders(form.getOrders());
		buyRepository.save(buy);
	}

	@Override
	public void delete(int id) {
		buyRepository.delete(id);	
	}

	@Override
	public Buy findById(int id) {
		return buyRepository.findById(id);
	}

}
