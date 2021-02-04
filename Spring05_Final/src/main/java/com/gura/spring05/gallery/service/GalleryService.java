package com.gura.spring05.gallery.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.gallery.dto.GalleryDto;

public interface GalleryService {
	public void getList(ModelAndView mView, HttpServletRequest request);
	//image save+db insert
	public void saveContent(GalleryDto dto, HttpServletRequest request);//여기서부터 3개가 어떤 차이가 있는지는 소스코드를 보면 알 수 있다.
	//image save
	public String saveImage(MultipartFile image, HttpServletRequest request);//ajax 업로드 할 때 아래의 것과 나눠서 사용하기 때문에 메소드가 3개
	//db insert
	public void addContent(GalleryDto dto, HttpSession session);
	public void getDetail(int num, ModelAndView mView);
}