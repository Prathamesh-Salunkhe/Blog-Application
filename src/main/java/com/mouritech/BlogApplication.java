package com.mouritech;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mouritech.config.AppConstants;
import com.mouritech.entity.Role;
import com.mouritech.repository.RoleRepository;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("pvs"));	
        try {
        	Role role = new Role();
        	role.setId(AppConstants.ADMIN);
        	role.setName("ADMIN");
        	
        	Role role1 = new Role();
        	role1.setId(AppConstants.NORMAL);
        	role1.setName("NORMAL");
        	
        	List<Role> roles = List.of(role,role1);
        	
        	List<Role> result = this.roleRepository.saveAll(roles);
        	
        	result.forEach(r->{
        		System.out.println(r.getName());
        	});
        	
        }catch(Exception e ) {
        	e.printStackTrace();
        }
	}
	
}

