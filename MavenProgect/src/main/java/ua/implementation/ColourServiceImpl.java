package ua.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.filterForm.ColourFilterForm;
import ua.repository.ColourRepository;
import ua.service.ColourService;
import ua.shop_e.Colour;
import ua.specification.ColourFilterAdapter;

@Service
public class ColourServiceImpl implements ColourService {

	@Autowired
	private ColourRepository colourRepository;
	
	@Override
	public void save(Colour colour) {
		if(colourRepository.findByName(colour.getName())==null){
		colourRepository.save(colour);
		}
	}

	@Override
	public void delete(int id) {
		colourRepository.delete(id);
		
	}

	@Override
	public List<Colour> findAll() {
		return colourRepository.findAll();
	}

	@Override
	public Colour findByName(String name) {
		return colourRepository.findByName(name);
	}

	@Override
	public Colour findOne(int id) {
		return colourRepository.findOne(id);
	}

	@Override
	public Page<Colour> findAll(Pageable pageable, ColourFilterForm form) {
		return colourRepository.findAll(new ColourFilterAdapter(form), pageable);
	}


}
