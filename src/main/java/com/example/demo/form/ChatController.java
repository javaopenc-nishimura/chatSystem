package com.example.demo.form;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.ChatDao;
import com.example.demo.entity.EntForm;

@Controller
public class ChatController {
	private final ChatDao chatdao;
	public ChatController(ChatDao chatdao) {
	this.chatdao = chatdao;
	}
	
	@RequestMapping("")
	public String home(Model model) {
		model.addAttribute("message","chatSystemへようこそ");
		return "index";
	}
	
	@RequestMapping("/view")
	public String view(@Validated Form form, BindingResult result, Model model) {
		model.addAttribute("title","チャットルーム");
		if(result.hasErrors()) {
			return "chat/view";
			}
		
		//データベース処理
		EntForm entform = new EntForm();
		entform.setName(form.getName1());
		entform.setComment(form.getComment());
		chatdao.insertDb(entform);
		
		//一覧表示
		List<EntForm> list = chatdao.searchDb();
		model.addAttribute("dbList",list);
		return "chat/view";
	}

}
