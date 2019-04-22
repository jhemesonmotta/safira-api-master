package com.cni.safira.api.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cni.safira.api.security.entity.User;
import com.cni.safira.api.security.enums.ProfileEnum;
import com.cni.safira.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

//	private UserRepository userRepository;
	
	public User valorSimulado() {
		User result = new User();
		result.setId("1");
    	result.setCpfResponsavel("93602987191");
    	result.setPassword("$2a$10$ZBm6Kfc5VkACuD3naOYHxOAPcUi6odBdeYbwLm2XB69IoIzEO3tHC");
    	result.setProfile(ProfileEnum.ROLE_ADMIN);
    	
    	return result;
	}
	
	public User findByEmail(String email) {
		return valorSimulado();
		//this.userRepository.findByEmail(email);
	}

	public User createOrUpdate(User user) {
		return valorSimulado();
		//this.userRepository.save(user);
	}

	public Optional<User>findById(String id) {
		return null;//this.userRepository.findById(id);
	}

	public void delete(String id) {
//		this.userRepository.deleteById(id);
	}

	public Page<User> findAll(int page, int count) {
//		Pageable pages = PageRequest.of(page, count);
		return null;//this.userRepository.findAll(pages);
	}
}
