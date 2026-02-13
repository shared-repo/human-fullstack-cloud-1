package com.myweb.bootmywebref.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myweb.bootmywebref.entity.GalleryEntity;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Integer> {

}
