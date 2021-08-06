package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.FabCarService;

@RestController
public class FabCarController {

   @Autowired
   FabCarService fabCarService;

   @GetMapping("test")
   public String test() {
      System.out.println("test");
      return "test ok";
   }

   @PostMapping("queryAllCar")
   public String queryAllCar() {
      System.out.println("queryAllCar");
      try {
         
         String result=fabCarService.queryAllCar();
         
         return "{\"info\":"+result+"}";

      
      } catch (Exception e) {
         return "fail";
      }
   }
   
   @PostMapping("querySubmit")
	public String querySubmit(HttpServletRequest request) {
		try {
			String carNumber = request.getParameter("carNumber");
			String result = fabCarService.querySubmit(carNumber);
//			JSONParser parser = new JSONParser();  
//			JSONArray array = (JSONArray) parser.parse(result);
			return "{\"info\":"+result+"}";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

}