package cl.ionix.user.controller.dto.base;

public class UserDto {

	private String name;
	private String username;
	private String email;
	private int phone;


	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public int getPhone() { return phone; }

	public void setPhone(int phone) { this.phone = phone; }
}
