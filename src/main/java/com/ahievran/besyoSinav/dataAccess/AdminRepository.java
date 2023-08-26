package com.ahievran.besyoSinav.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahievran.besyoSinav.entities.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Integer>{
	Admin findByUsername(String username);
}
