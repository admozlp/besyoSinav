package com.ahievran.besyoSinav.business.concretes;

import java.util.Collection;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.AdminService;
import com.ahievran.besyoSinav.business.requests.CreateAdminRequest;
import com.ahievran.besyoSinav.core.utilities.mapper.MapperService;
import com.ahievran.besyoSinav.dataAccess.AdminRepository;
import com.ahievran.besyoSinav.entities.Admin;
import com.ahievran.besyoSinav.entities.Role;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AdminManager implements AdminService{
	
	
	private AdminRepository adminRepository;
	private MapperService mapperService;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminManager(@Lazy BCryptPasswordEncoder passwordEncoder, AdminRepository adminRepository, MapperService mapperService) {
		// TODO Auto-generated constructor stub
		this.adminRepository = adminRepository;
		this.mapperService = mapperService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Admin add(CreateAdminRequest createAdminRequest) {
		Admin admin = new Admin();
		admin = this.mapperService.forRequest()
				.map(createAdminRequest, Admin.class);
		
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return this.adminRepository.save(admin);
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = this.adminRepository.findByUsername(username);
		
		if(admin == null) {
			throw new UsernameNotFoundException("Kullanıcı adı veya parola hatalı!");
		}
		return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),mapRolesToAuthorities(admin.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
}
