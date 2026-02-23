package com.humanda6.demoweb2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.mail.internet.MimeMessage;
import lombok.Setter;

@Controller
public class MailController {
	
//	@Autowired
//	private JavaMailSender mailSender;
	
	@Setter(onMethod_ = { @Autowired })
	private JavaMailSender mailSender;

	@GetMapping(path = { "/send-email" })
	public String sendMail() {
		
		boolean success = true;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			
			messageHelper.setFrom("ohchihooooon@naver.com");
			messageHelper.setTo(new String[] { "oh.chi.hooooon@gmail.com", "shared.repo.z@gmail.com" });
			message.setSubject("메일 보내기 연습");
			message.setContent(
					String.format("<html><body><h1>%s</h1></body></html>", "메일을 확인해 주세요"), 
					"text/html;charset=utf-8");
			
			mailSender.send(message);
		} catch (Exception ex) {
			ex.printStackTrace();
			success = false;
		}
		
		return "redirect:/";
	}
	
}
