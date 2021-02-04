package com.gura.spring05.gallery.dao;

import java.util.List;

import com.gura.spring05.gallery.dto.GalleryDto;

public interface GalleryDao {
	public GalleryDto getData(int num);
	public void insert(GalleryDto dto);
	public List<GalleryDto> getList(GalleryDto dto);
	public int getCount();
}