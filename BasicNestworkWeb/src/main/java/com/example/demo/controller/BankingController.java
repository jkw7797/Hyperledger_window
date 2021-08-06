package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BankingService;

@RestController
public class BankingController {
	
	@Autowired
	BankingService bankingService;
	
	@PostMapping("getHistory")//거래내역보기
	public String getHistory(HttpServletRequest request) {			
			try {
				String history_id=request.getParameter("history_id");
				
				String result=bankingService.getHistory(history_id);
				return result;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail";
			}		
	}
	
	@PostMapping("sendAsset")//이체
	public String sendAsset(HttpServletRequest request) {			
			try {
				String from_id=request.getParameter("from_id");
				String to_id=request.getParameter("to_id");
				String amount=request.getParameter("amount");
				bankingService.sendAsset(from_id,to_id,amount);
				return "이체 성공";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail";
			}		
	}
	
	@PostMapping("setAsset")//적립
	public String setAsset(HttpServletRequest request) {			
			try {
				String id=request.getParameter("id");
				String amount=request.getParameter("amount");
				bankingService.setAsset(id,amount);
				return "자산 적립 성공";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail";
			}		
	}
	
	@PostMapping("getAsset")//조회
	public String getAsset(HttpServletRequest request) {			
			try {
				String id=request.getParameter("id");
				String asset=bankingService.getAsset(id);
				return id+"님의 자산 : "+asset;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail";
			}		
	}

}