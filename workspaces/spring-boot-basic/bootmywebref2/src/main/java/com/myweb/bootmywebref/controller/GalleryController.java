package com.myweb.bootmywebref.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.bootmywebref.common.Util;
import com.myweb.bootmywebref.dto.GalleryDto;
import com.myweb.bootmywebref.service.GalleryService;

@Controller
@RequestMapping(path = { "/gallery" })
public class GalleryController {
	
	private GalleryService galleryService;
	public GalleryController(GalleryService galleryService) {
		this.galleryService = galleryService;
	}

	@GetMapping(path = { "/list" })
	public String list(Model model) {
		
		List<GalleryDto> pictures = galleryService.findAll();
		model.addAttribute("pictures", pictures);
		
		return "gallery/list";
		
	}
	
	@GetMapping(path = { "/register" })
	public String registerForm() {
		
		return "gallery/register";
		
	}
	
	// final String UPLOAD_PATH = "D:\\upload-pictures";
	final String UPLOAD_PATH = "/work/upload-pictures";
	
	@PostMapping(path = { "/register" })
	public String register(
			GalleryDto gallery,
			@RequestParam("picture") MultipartFile picture) {
		
		if (picture != null && picture.getOriginalFilename().length() > 0) {
			String savedFileName = Util.makeUniqueFileName(picture.getOriginalFilename());
			
			File file = new File(UPLOAD_PATH, savedFileName);
			try {
				
				gallery.setUserFileName(picture.getOriginalFilename());
				gallery.setSavedFileName(savedFileName);
				
				galleryService.registerGallery(gallery);
				
				picture.transferTo(file);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/gallery/list";
		
	}
	
	@GetMapping(path = { "/detail/{idx}" })
	public String detail(
			@PathVariable("idx") int idx,
			Model model) {
		
		GalleryDto dto = galleryService.findGalleryByIdx(idx);
		if (dto == null) {
			return "redirect:/gallery/list";
		}
		// System.out.println("---------------> " + dto);
		
		model.addAttribute("picture", dto);
		
		return "gallery/detail";
	}
	
	
}






