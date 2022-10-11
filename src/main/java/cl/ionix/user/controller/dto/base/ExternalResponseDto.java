package cl.ionix.user.controller.dto.base;

import java.lang.reflect.Array;

public class ExternalResponseDto {

	private String description;
	private int responseCode;
	private Array result;
	private int elapsedTime;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public Array getResult() {
		return result;
	}

	public void setResult(Array result) {
		this.result = result;
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}
