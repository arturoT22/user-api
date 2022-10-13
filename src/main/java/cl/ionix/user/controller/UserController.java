package cl.ionix.user.controller;
import cl.ionix.user.controller.dto.base.BaseResponseDto;
import cl.ionix.user.controller.dto.base.ResultCodeType;
import cl.ionix.user.controller.dto.base.UserDto;
import cl.ionix.user.core.bo.UserBo;
import cl.ionix.user.core.services.ExternalService;
import cl.ionix.user.core.services.UserService;
import cl.ionix.user.exception.UserFoundException;
import cl.ionix.user.exception.UserNotFoundException;
import cl.ionix.user.exception.UsersDatabaseIsEmptyException;
import cl.ionix.user.util.Constant;
import cl.ionix.user.util.EntityUtilities;
import cl.ionix.user.util.MessageSourceUtilities;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Validated
public class UserController {

	@Autowired
	private UserService baseService;

	@Autowired
	private ExternalService externalService;


	@GetMapping(value="code/{param}",produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Consumo servicio externo", response = BaseResponseDto.class)
	public String externalService(@PathVariable("param") String param) throws UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
		return String.valueOf(externalService.search(param));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creacion de usuario", response = BaseResponseDto.class)
	public BaseResponseDto userCreate(@Valid @RequestBody UserDto User) throws UserFoundException {
			baseService.postUserCreate(EntityUtilities.copyObjectFrom(User, UserBo.class));
			return new BaseResponseDto<>(ResultCodeType.SUCCESS, MessageSourceUtilities.getValue(Constant.MSGE_SUCCESS_GENERIC_OPERATION));

	}

	@GetMapping(value="users",produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Lista de Usuarios", response = BaseResponseDto.class)
	public BaseResponseDto usersList() throws UsersDatabaseIsEmptyException {
		List<UserDto> userDtoList = new ArrayList<>();
		for (UserBo userBo: baseService.getUserList()) {
			userDtoList.add(EntityUtilities.copyObjectFrom(userBo, UserDto.class));
		}
		return new BaseResponseDto<>(ResultCodeType.SUCCESS, MessageSourceUtilities.getValue(Constant.MSGE_SUCCESS_GENERIC_OPERATION),userDtoList);
	}

	@DeleteMapping(value="{email}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Eliminar usuario por el email", response = BaseResponseDto.class)
	public BaseResponseDto deleteUserByEmail(@PathVariable("email") String email) throws UserNotFoundException {
		baseService.deleteUserByEmail(email);
		return new BaseResponseDto<>(ResultCodeType.SUCCESS, MessageSourceUtilities.getValue(Constant.MSGE_SUCCESS_GENERIC_OPERATION));
	}


	@GetMapping(value="{email}",produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Buscar usuario por el email", response = BaseResponseDto.class)
	public BaseResponseDto findUserByEmail(@PathVariable("email") String email) throws UserNotFoundException {
			return new BaseResponseDto<>(ResultCodeType.SUCCESS, MessageSourceUtilities.getValue(Constant.MSGE_SUCCESS_GENERIC_OPERATION),EntityUtilities.copyObjectFrom(baseService.getUserByEmail(email), UserDto.class));
	}

}
