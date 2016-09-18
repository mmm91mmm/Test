package ua.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.filterForm.SizeFilterForm;
import ua.repository.SizeRepository;
import ua.service.SizeService;
import ua.shop_e.Size;
import ua.specification.SizeFilterAdapter;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private  SizeRepository  sizeRepository;
	
	@Override
	public void save(Size size) {
		if(sizeRepository.findByName(size.getName())==null){
		sizeRepository.save(size);
		}
	}

	@Override
	public void delete(int id) {
		sizeRepository.delete(id);
		
	}

	@Override
	public List<Size> findAll() {
		return sizeRepository.findAll();
	}

	@Override
	public Size findByName(String name) {
		return sizeRepository.findByName(name);
	}

	@Override
	public Size findOne(int id) {
		return sizeRepository.findOne(id);
	}

	@Override
	public Page<Size> findAll(Pageable pageable, SizeFilterForm form) {
		return sizeRepository.findAll(new SizeFilterAdapter(form), pageable);
	}

}
