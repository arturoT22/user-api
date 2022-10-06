package cl.ionix.user.controller;

import cl.ionix.user.controller.dto.base.BaseResponseDto;
import cl.ionix.user.core.bo.UserBo;
import cl.ionix.user.core.services.ExampleService;
import cl.ionix.user.data.entity.UserEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Validated
public class UserController {

	@Autowired
	private ExampleService exampleService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creacion de usuario", response = BaseResponseDto.class)
	public void UserCreate(@RequestBody UserBo User) {
		exampleService.postUserCreate(User);
		}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Lista de Usuarios", response = BaseResponseDto.class)
	public List<UserEntity> UsersList() {
		return exampleService.getUserList();
		}
	/*
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Ejemplo", response = BaseResponseDto.class)
	public BaseResponseDto<ResponseExampleDto> example() {
		ResponseExampleBo responseExamplebo = exampleService.getExample();
		return new BaseResponseDto<>(ResultCodeType.SUCCESS, MessageSourceUtilities.getValue(Constant.MSGE_SUCCESS_GENERIC_OPERATION), EntityUtilities.copyObjectFrom(responseExamplebo,ResponseExampleDto.class ));
	}*/

}
