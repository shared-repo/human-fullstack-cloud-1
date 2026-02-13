package com.myweb.bootmywebref.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.bootmywebref.dto.GalleryDto;
import com.myweb.bootmywebref.entity.GalleryEntity;
import com.myweb.bootmywebref.repository.GalleryRepository;

@Service
public class GalleryServiceImpl implements GalleryService {
	
	private GalleryRepository galleryRepository;
	public GalleryServiceImpl(GalleryRepository galleryRepository) {
		this.galleryRepository = galleryRepository;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void registerGallery(GalleryDto gallery) {

		GalleryEntity entity = GalleryEntity.fromDto(gallery);
		galleryRepository.save(entity);
		
		// int x = 10 / 0;
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<GalleryDto> findAll() {
		List<GalleryEntity> entities = galleryRepository.findAll();
		ArrayList<GalleryDto> dtos = new ArrayList<>();
		for (GalleryEntity entity : entities) {
			GalleryDto dto = entity.toDto();
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	@Transactional(readOnly = true)
	public GalleryDto findGalleryByIdx(int idx) {
		Optional<GalleryEntity> entity = galleryRepository.findById(idx);
		GalleryEntity entity2 = entity.orElseGet(() -> null); // 있으면 값, 없으면 lambda 함수의 반환값
		return entity2.toDto();
	}

}
