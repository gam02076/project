package com.spring_boot_mybatis.project.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring_boot_mybatis.project.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	
	//로그인 처리 : id 와 pwd 전달 받아서 로그인 인증 완료 후 세션 설정
	@ResponseBody
	@RequestMapping("/login")
	public String loginCheck(@RequestParam HashMap<String, Object>param,
			                 HttpSession session) {
		String memId = service.loginCheck(param);
		String result = "fail";
		//로그인 체크 결과
		//아이디와 비밀번호 일치하면 (로그인 성공화면)
		if(memId !=null) {
			session.setAttribute("sid", memId);
			result = "success";
		}
		return result;
	}
	
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//세션 무효화
		session.invalidate();
		//index로 포워딩
		
		return "redirect:/";
	}
}
