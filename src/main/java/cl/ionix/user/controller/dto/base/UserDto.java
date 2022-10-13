package cl.ionix.user.controller.dto.base;

import javax.validation.constraints.*;

public class UserDto {

	@NotBlank(message = "Debe ingresar un nombre")
	@Size(min = 2, max = 20, message = "Su nombre debe tener entre 2 y 20 caracteres")
	@Pattern(regexp = "[a-zA-Z ]+", message = "Debe contener solo letras")
	private String name;

	@NotBlank(message = "Debe ingresar un nombre de usuario")
	@Size(min = 2, max = 20, message = "Su nombre de usuario debe tener entre 2 y 20 caracteres")
	@Pattern(regexp = "[a-zA-Z]+", message = "Debe contener solo letras y sin espacios")
	private String username;

	@NotNull(message = "Debe ingresar un correo")
	@Size(min = 2, max = 50, message = "Su nombre debe tener mas de 1 caracter")
	@Email(message = "email no cumple el formato correcto")
	private String email;

	@NotNull(message = "Debe ingresar un numero")
	@Min(value = 200000000, message = "Debe ingresar un numero valido, ejemplo 912345678")
	@Max(value = 999999999, message = "Debe ingresar un numero valido, ejemplo 912345678")
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
