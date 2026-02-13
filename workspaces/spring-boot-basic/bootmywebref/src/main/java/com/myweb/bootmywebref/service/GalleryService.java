package com.myweb.bootmywebref.service;

import java.util.List;

import com.myweb.bootmywebref.dto.GalleryDto;

public interface GalleryService {

	void registerGallery(GalleryDto gallery);

	List<GalleryDto> findAll();

	GalleryDto findGalleryByIdx(int idx);


}
