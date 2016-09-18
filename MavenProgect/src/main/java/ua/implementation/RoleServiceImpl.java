package ua.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.filterForm.RoleFilterForm;
import ua.repository.RoleRepository;
import ua.service.RoleService;
import ua.shop_e.Role;
import ua.specification.RoleFilterAdapter;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private  RoleRepository  roleRepository;

	@Override
	public void delete(int id) {
		roleRepository.delete(id);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role findOne(int id) {		
		return roleRepository.findOne(id);
	}

	@Override
	public void save(Role role) {
		if(roleRepository.findByName(role.getName())==null){
			roleRepository.save(role);
		}
		
	}

	@Override
	public Page<Role> findAll(Pageable pageable, RoleFilterForm form) {
		return roleRepository.findAll(new RoleFilterAdapter(form), pageable);
	}

}
