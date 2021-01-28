package com.gura.spring04;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadTestController {
	/*
	 * <input type="file" name="myFile"/>
	 * 
	 * 을 통해서 전송되는 파일 정보는 MultipartFile 객체로 받으면 된다.
	 * name 속성의 value "myFile"과 
	 * MultipartFile 지역 변수명 myFile 과 같아야 한다.
	 */
	@RequestMapping("/upload")
	public String upload(@RequestParam String title, MultipartFile myFile, HttpServletRequest request) {//MultipartFile 
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		//파일을 저장할 실제 경로 "/webapp/upload/"
		String realPath=request.getServletContext().getRealPath("/upload");
		File f=new File(realPath);
		if(!f.exists()) {//만일 존재하지 않으면
			f.mkdir();//폴더를 만든다.
		}
		//저장할 파일명을 구성한다. 중복안되려고 currentTimemillis() 함.
		String saveFileName=System.currentTimeMillis()+orgFileName;
		//저장할 파일의 전체 경로를 구성한다.
		String path=realPath+File.separator+saveFileName;
		try {
			//임시폴더에 업로드된 파일을 원하는 위치에 원하는 파일명으로 이동 시킨다.
			myFile.transferTo(new File(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//전송된 정보를 view page 에서 확인하기 위해 request scope 에 담기
		request.setAttribute("orgFileName", orgFileName);
		request.setAttribute("saveFileName", saveFileName);
		request.setAttribute("fileSize", fileSize);
		request.setAttribute("path", path);
		request.setAttribute("title", title);
		
		return "upload";
	}
}
