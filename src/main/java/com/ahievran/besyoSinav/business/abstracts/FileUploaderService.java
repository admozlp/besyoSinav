package com.ahievran.besyoSinav.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploaderService {
	public void uploadFile(MultipartFile file);

}
