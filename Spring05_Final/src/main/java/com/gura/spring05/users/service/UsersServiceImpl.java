package com.gura.spring05.users.service;

import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dao.UsersDao;
import com.gura.spring05.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDao dao;
	
	@Override
	public void addUser(UsersDto dto) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		//입력한 비밀번호를 암호화 한다.
		String encodedPwd=encoder.encode(dto.getPwd());
		//UsersDto 에 다시 넣어준다.
		dto.setPwd(encodedPwd);
		//저장
		dao.insert(dto);
	}

	@Override
	public boolean isExistId(String inputId) {
		//id 존재 여부를 리턴해준다.
		return dao.isExist(inputId);
	}

	@Override
	public void loginformLogic(HttpServletRequest request, ModelAndView mView) {
		// GET 방식 파라미터 url 이라는 이름으로 전달되는 값이 있는지 읽어와보기
		String url=request.getParameter("url");
		//만일 넘어오는 값이 없다면 
		if(url==null){
			//로그인 후에 home.do 요청이 되도록 절대 경로를 구성한다.
			String cPath=request.getContextPath();
			url=cPath+"/home.do";
		}
		//쿠키에 저장된 아이디와 비밀번호를 담을 변수
		String savedId="";
		String savedPwd="";
		//쿠키에 저장된 값을 위의 변수에 저장하는 코드를 작성해 보세요.
		Cookie[] cooks=request.getCookies();
		if(cooks!=null){
			//반복문 돌면서 쿠키객체를 하나씩 참조해서 
			for(Cookie tmp: cooks){
				//저장된 키값을 읽어온다.
				String key=tmp.getName();
				//만일 키값이 savedId 라면 
				if(key.equals("savedId")){
					//쿠키 value 값을 savedId 라는 지역변수에 저장
					savedId=tmp.getValue();
				}
				if(key.equals("savedPwd")){
					savedPwd=tmp.getValue();
				}
			}
		}		
		//view page 에서 필요한 데이터를 담는다.
		mView.addObject("url", url);
		mView.addObject("savedId", savedId);
		mView.addObject("savedPwd", savedPwd);
	}

	@Override
	public void loginLogic(HttpServletRequest request, HttpServletResponse response) {
		//로그인후 가야하는 목적지 정보
		String url=request.getParameter("url");
		//로그인 실패를 대비해서 목적지 정보를 인코딩한 결과도 준비 한다.
		String encodedUrl=URLEncoder.encode(url);
		//1. 폼전송되는 아이디와 비밀번호를 읽어온다.
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		//유효한 정보인지 여부를 담을 지역변수를 만들고 초기값 false 지정
		boolean isValid=false;
		
		//2. 아이디를 이용해서 암호화된 비밀번호를 SELECT 한다.
		String savedPwd=dao.getPwd(id);
		//3. 비밀번호가 만일 NULL 이 아니면 (존재하는 아이디)
		if(savedPwd != null) {
			//4. 폼 전송되는 비밀번호와 일치하는지 확인한다. (isValid true,false 갈림)
			isValid=BCrypt.checkpw(pwd, savedPwd);
		}
		
		//3. 유효한 정보이면 로그인 처리를 하고 응답 그렇지 않으면 아이디혹은 비밀번호가 틀렸다고 응답
		if(isValid) {
			//HttpSession 객체를 이용해서 로그인 처리를 한다.
			request.getSession().setAttribute("id", id);
			//HttpSession session 하거나 request.getSession 하면 된다.
		}
		
		//체크박스를 체크 하지 않았으면 null 이다. 
		String isSave=request.getParameter("isSave");
		
		if(isSave == null){//체크 박스를 체크 안했다면
			//저장된 쿠키 삭제 
			Cookie idCook=new Cookie("savedId", id);
			idCook.setMaxAge(0);//삭제하기 위해 0 으로 설정 
			response.addCookie(idCook);
			
			Cookie pwdCook=new Cookie("savedPwd", pwd);
			pwdCook.setMaxAge(0);
			response.addCookie(pwdCook);
		}else{//체크 박스를 체크 했다면 
			//아이디와 비밀번호를 쿠키에 저장
			Cookie idCook=new Cookie("savedId", id);
			idCook.setMaxAge(60*60*24);//하루동안 유지
			response.addCookie(idCook);
			
			Cookie pwdCook=new Cookie("savedPwd", pwd);
			pwdCook.setMaxAge(60*60*24);
			response.addCookie(pwdCook);
		}		
		//view page 에서 필요한 데이터를 request 에 담고
		request.setAttribute("encodedUrl", encodedUrl);
		request.setAttribute("url", url);
		request.setAttribute("isValid", isValid);
	}

	@Override
	public void getInfo(ModelAndView mView, HttpSession session) {
		//로그인된 아이디를 읽어와서
		String id=(String)session.getAttribute("id");
		//개인정보를 읽어온다
		UsersDto dto=dao.getData(id);
		//읽어온 정보를 ModelAndView 객체에 담아준다.
		mView.addObject("dto", dto);
	}

	@Override
	public void deleteUser(HttpSession session) {
		//로그인된 아이디를 읽어온다.
		String id=(String)session.getAttribute("id");
		//DB 에서 삭제
		dao.delete(id);
		//로그아웃 처리
		session.removeAttribute("id");
	}

	@Override
	public void updateUserPwd(ModelAndView mView, UsersDto dto, HttpSession session) {
		//로그인된 아이디를 읽어와서
		String id=(String)session.getAttribute("id");
		//1. 예전 비밀번호가 맞는지 확인한다.
		//유효한 정보인지 여부를 담을 지역변수를 만들고 초기값 false 지정
		boolean isValid=false;
		
		//아이디를 이용해서 암호화된 비밀번호를 SELECT 한다.
		String savedPwd=dao.getPwd(id);
		//비밀번호가 만일 NULL 이 아니면 (존재하는 아이디)
		if(savedPwd != null) {
			//폼 전송되는 비밀번호와 일치하는지 확인한다. (isValid true,false 갈림)
			isValid=BCrypt.checkpw(dto.getPwd(), savedPwd);
		}		
		//2. 만일 맞다면
		if(isValid) {
			//3. 새 비밀번호를 암호화해서
			String newPwd=new BCryptPasswordEncoder().encode(dto.getNewPwd());
			//4. dto 에 아이디와 새 비밀번호를 담고 (set!주의)
			dto.setId(id);
			dto.setNewPwd(newPwd);
			//5. 수정 반영한다.
			dao.updatePwd(dto);
			//로그아웃 처리를 한다.
			session.removeAttribute("id");
		}
		
		//성공 여부를 ModelAndView 객체에 담는다. request.setAttribute()
		mView.addObject("isSuccess", isValid);
		
	}

	@Override
	public void saveProfileIamge(MultipartFile image, HttpServletRequest request) {
		//원본 파일명
		String orgFileName=image.getOriginalFilename();
		
		//파일을 저장할 실제 경로 "/webapp/upload/"
		String realPath=request.getServletContext().getRealPath("/upload");
		File f=new File(realPath);
		if(!f.exists()) {//만일 존재하지 않으면
			f.mkdir();//폴더를 만든다.
		}
		//저장할 파일명을 구성한다. 파일명 중복안되려고 currentTimemillis() 함.
		String saveFileName=System.currentTimeMillis()+orgFileName;
		//저장할 파일의 전체 경로를 구성한다. separator 운영체제의 구분을 위해 사용
		String path=realPath+File.separator+saveFileName;
		try {
			File f2=new File(path);
			//임시폴더에 업로드된 파일을 원하는 위치에 원하는 파일명으로 이동 시킨다. 임시폴더에 업로드 된 부분은 운영체제가 알아서 해준 것이라 소스코드에 없다. 운영체제가 알아서 해준 걸 myFile.transferTo(new File(path)); 통해서 다시 옮기는 것.
			image.transferTo(f2);//전체 경로를 이용해서 파일객체 생성. 원하는 위치에 파일 객체를 생성. 특정파일에 access 할 수 있는 파일 객체
		}catch(Exception e) {
			e.printStackTrace();
		}
		//DB 에 저장할 이미지의 경로
		String profile="/upload/"+saveFileName;
		//로그인된 아이디
		String id=(String)request.getSession().getAttribute("id");
		//위의 두 데이터를 DB에 업데이트 시켜준다.
		//수정할 정보를 dto 에 담기
		UsersDto dto=new UsersDto();
		dto.setId(id);
		dto.setProfile(profile);
		//dao 를 이용해서 수정 반영하기
		dao.updateProfile(dto);
		
	}

	@Override
	public void updateUser(UsersDto dto, HttpSession session) {
		//로그인 된 아이디를 읽어온다.
		String id=(String)session.getAttribute("id");
		//dto 에 담는다.
		dto.setId(id);
		//dao 를 이용해서 DB 에 수정 반영한다.
		dao.update(dto);
	}

	
}
