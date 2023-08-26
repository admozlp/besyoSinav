package com.ahievran.besyoSinav.business.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminRequest {
	@NotNull
	@NotBlank
	@Size(min=2, max=30)
	private String username;
	
	@NotNull
	@NotBlank
	@Size(min=2, max=30)
	private String password;
}
