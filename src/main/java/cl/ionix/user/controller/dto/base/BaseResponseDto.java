package cl.ionix.user.controller.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"result", "message", "code"})
public class BaseResponseDto<T> extends MessageResponseDto{
	private ResultCodeType result;
	T data;
	List<String> errors;

	public ResultCodeType getResult() {
		return result;
	}

	public void setResult(ResultCodeType result) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public BaseResponseDto(ResultCodeType result, String message, T data) {
		super(message);
		this.result = result;
		this.data = data;
	}

	public BaseResponseDto(ResultCodeType result, String message) {
		super(message);
		this.result = result;
	}
	public BaseResponseDto(ResultCodeType result, String message, List<String> errors) {
		super(message);
		this.result = result;
		this.errors = errors;
	}

	public BaseResponseDto(ResultCodeType result, MessageResponseDto messageResponseDto, T data) {
		super(messageResponseDto);
		this.result = result;
		this.data = data;
	}

	public BaseResponseDto(ResultCodeType result, MessageResponseDto messageResponseDto, List<String> errors) {
		super(messageResponseDto);
		this.result = result;
		this.errors = errors;
	}

	public BaseResponseDto(ResultCodeType result, MessageResponseDto messageResponseDto) {
		super(messageResponseDto);
		this.result = result;
	}

}
