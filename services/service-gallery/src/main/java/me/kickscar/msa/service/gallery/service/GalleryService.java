package me.kickscar.msa.service.gallery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.kickscar.msa.service.gallery.repository.GalleryRepository;
import me.kickscar.msa.service.gallery.vo.GalleryVo;

@Service
public class GalleryService {

	private final GalleryRepository galleryRepository;

	public GalleryService(GalleryRepository galleryRepository) {
		this.galleryRepository = galleryRepository;
	}

	public List<GalleryVo> getGalleryImages() {
		return galleryRepository.findAll();
	}
	
	@Transactional
	public Boolean deleteGalleryImage(Long no) {
		return galleryRepository.delete(no);
	}
	
	@Transactional
	public Boolean addGalleryImage(GalleryVo vo) {
		return galleryRepository.insert(vo);
	}
}
