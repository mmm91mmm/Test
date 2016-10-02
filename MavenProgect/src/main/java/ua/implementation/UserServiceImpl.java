package ua.implementation;

//import java.util.regex.Pattern;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import ua.repository.UserRepository;
//import ua.service.UserService;
//import ua.shop_e.Role;
//import ua.shop_e.User;
//
////@Service("userDetailsService")
//public class UserServiceImpl implements UserService, UserDetailsService{
//
//	@Autowired
//	private UserRepository repository;
//	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
//
//	@Override
//	public User findByLogin(String login) {
//		return repository.findByLogin(login);
//	}
//
//	@Override
//	public void save(User user) {
//		user.setRole(Role.ROLE_USER);
//		user.setPassword(encoder.encode(user.getPassword()));
//		repository.save(user);
//	}
//	
//	@PostConstruct
//	public void saveAdmin(){
//		User user = repository.findOne(1);
//		if(user==null){
//			user = new User();
//			user.setRole(Role.ROLE_ADMIN);
//			user.setPassword(encoder.encode("admin"));
//			user.setLogin("admin");
//			user.setId(1);
//			repository.save(user);
//		}
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String login)
//			throws UsernameNotFoundException {
//		if(Pattern.matches("^[0-9]{1,8}$", login)){
//			return repository.findOne(Integer.valueOf(login));
//		}
//		return repository.findByLogin(login);
//	}
//
//	@Override
//	public User findById(int id) {
//		return repository.findOne(id);
//	}
//
//	public void setRepository(UserRepository repository) {
//		this.repository = repository;
//	}
//
//	public void setEncoder(BCryptPasswordEncoder encoder) {
//		this.encoder = encoder;
//	}
//}