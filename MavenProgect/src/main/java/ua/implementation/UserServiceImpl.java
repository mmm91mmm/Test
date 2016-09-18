package ua.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.repository.UserRepository;
import ua.service.UserService;
import ua.shop_e.User;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private  UserRepository  userRepository;
	
	
	@Override
	public void save(User form) {
			User user = new User();
			user.setLogin(form.getLogin());
			user.setId(form.getId());
			user.setMail(form.getMail());
			user.setPassword(form.getPassword());
			user.setRole(form.getRole());
			userRepository.save(user);
	}
	
	
	@Override
	public void delete(int id) {
		userRepository.delete(id);
		
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByMail(String mail) {
		return userRepository.findByMail(mail);
	}

	@Override
	public User findOneUser(int id) {
		return userRepository.findOne(id);
	}


	@Override
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}


	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

}
