package cn.brank.chuyiting.chuyitingmsp.result;

import java.io.Serializable;
import java.util.Map;

public class Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	private Map<String, Object> payload;
	
	public static Result success(String message) {
		Result result = new Result();
		result.setSuccess(true);
		result.setMessage(message);
		return result;
	}
	
	public static Result success(String message, Map<String, Object> payload) {
		Result result = new Result();
		result.setSuccess(true);
		result.setMessage(message);
		result.setPayload(payload);
		return result;
	}
	
	public static Result fail(String message) {
		Result result = new Result();
		result.setSuccess(false);
		result.setMessage(message);
		return result;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Result [success=" + success + ", message=" + message + ", payload=" + payload + "]";
	}

}
