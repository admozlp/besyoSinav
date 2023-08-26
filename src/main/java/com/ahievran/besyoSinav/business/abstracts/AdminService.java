package com.ahievran.besyoSinav.business.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ahievran.besyoSinav.business.requests.CreateAdminRequest;
import com.ahievran.besyoSinav.entities.Admin;

public interface AdminService extends UserDetailsService {
	Admin add(CreateAdminRequest createAdminRequest);
}