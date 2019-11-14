package com.zzg.jreport.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JreportResponse<T> {

	private int code = HttpStatus.OK.value();

	private String msg = "success";

	private T data;
	
	public JreportResponse(){
	}

	public JreportResponse(T data) {
		this.data = data;
	}

	public JreportResponse(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public JreportResponse(int code, T data) {
		this.code = code;
		this.data = data;
	}
	
	public static JreportResponse ok(){
		return new JreportResponse();
	}
	
	public static JreportResponse ok(Object data){
		JreportResponse response = new JreportResponse();
		response.data = data;
		return response;
	}
	
	public static JreportResponse ok(int code, String msg){
		JreportResponse response = new JreportResponse();
		response.code = code;
		response.msg = msg;
		return response;
	}
	
	public static JreportResponse ok(int code, Object data){
		JreportResponse response = new JreportResponse();
		response.code = code;
		response.data = data;
		return response;
	}
	
	public static JreportResponse error(){
		JreportResponse response = new JreportResponse();
		response.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
		response.msg = "fail";
		return response;
	}
	
	public static JreportResponse error(Object data){
		JreportResponse response = new JreportResponse();
		response.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
		response.data = data;
		return response;
	}
	
	public static JreportResponse error(int code, String msg){
		JreportResponse response = new JreportResponse();
		response.code = code;
		response.msg = msg;
		return response;
	}
	
	public static JreportResponse error(int code, Object data){
		JreportResponse response = new JreportResponse();
		response.code = code;
		response.data = data;
		response.msg = "fail";
		return response;
	}
	
	
	
	

}
