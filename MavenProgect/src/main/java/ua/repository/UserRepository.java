package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.shop_e.User;

public interface UserRepository extends JpaRepository <User,Integer>{

//	User findByMail(String mail);
//	
//	@Override
//	@Query("SELECT u FROM User u LEFT JOIN FETCH u.role")
//	List<User> findAll();
//	
//	@Query("SELECT u FROM User u LEFT JOIN FETCH u.role where u.id=:id")
//	User findOneUser(@Param("id")int id);
//
//	User findByLogin(String login);
//	
//	@Query(value = "SELECT r FROM User r LEFT JOIN FETCH r.role",
//			countQuery="SELECT count(r.id) FROM User r LEFT JOIN r.role")
//	Page<User> findAll(Pageable pageable);
	
	User findByLogin(String login);
	
}
