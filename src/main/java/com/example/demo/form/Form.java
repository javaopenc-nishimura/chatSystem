package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class Form {
	@NotBlank(message="文字を入力してください")
	private String name1;
	
	@Size(min=1, max=500, message="1文字以上～500文字以内で入力してください" )
	private String comment;
	
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
