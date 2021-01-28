package com.gura.spring04;

import org.springframework.web.multipart.MultipartFile;

public class FileDto {
	//필드
	private String title;
	/*
	 * <input type="file" name="myFile"/>
	 * 의 정보를 저장할 MultipartFile
	 */
	private MultipartFile myFile;
	
	//디폴트 생성자
	public FileDto() {}

	//생성자
	public FileDto(String title, MultipartFile myFile) {
		super();
		this.title = title;
		this.myFile = myFile;
	}

	//getter setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MultipartFile getMyFile() {
		return myFile;
	}

	public void setMyFile(MultipartFile myFile) {
		this.myFile = myFile;
	}

}
