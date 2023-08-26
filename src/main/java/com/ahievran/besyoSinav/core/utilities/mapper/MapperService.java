package com.ahievran.besyoSinav.core.utilities.mapper;

import org.modelmapper.ModelMapper;

public interface MapperService {
	ModelMapper forRequest();
	ModelMapper forResponse();
}
