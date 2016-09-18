package ua.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.filterForm.ManufacturerFilterForm;
import ua.repository.ManufacturerRepository;
import ua.service.ManufacturerService;
import ua.shop_e.Manufacturer;
import ua.specification.ManufacturerFilterAdapter;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
	
	@Autowired
	private  ManufacturerRepository  manufacturerRepository;

	@Override
	public Manufacturer findByName(String name) {
		return manufacturerRepository.findByName(name);
	}

	@Override
	public void save(Manufacturer manufacturer) {
		if(manufacturerRepository.findByName(manufacturer.getName())==null){
			manufacturerRepository.save(manufacturer);
		}
	}
	
	@Override
	public void delete(int id) {
		manufacturerRepository.delete(id);
		
	}

	@Override
	public List<Manufacturer> findAll() {
		return manufacturerRepository.findAll();
	}

	@Override
	public Manufacturer findOne(int id) {
		return manufacturerRepository.findOne(id);
	}

	@Override
	public Page<Manufacturer> findAll(Pageable pageable, ManufacturerFilterForm form) {
		return manufacturerRepository.findAll(new ManufacturerFilterAdapter(form), pageable);
	}

}
