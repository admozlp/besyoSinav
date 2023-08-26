package com.ahievran.besyoSinav.business.abstracts;

import java.io.InputStream;

import java.io.IOException;

public interface DownloadExampleExcelService {
	public InputStream getInputStream() throws IOException;
}
