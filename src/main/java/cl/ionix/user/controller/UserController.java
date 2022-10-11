package cl.ionix.user.controller;
import cl.ionix.user.controller.dto.base.BaseResponseDto;
import cl.ionix.user.controller.dto.base.ResultCodeType;
import cl.ionix.user.controller.dto.base.UserDto;
import cl.ionix.user.core.bo.UserBo;
import cl.ionix.user.core.services.UserService;
import cl.ionix.user.util.Constant;
import cl.ionix.user.util.EntityUtilities;
import cl.ionix.user.util.MessageSourceUtilities;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Validated
public class UserController {

	@Autowired
	private UserService baseService;

	@Autowired
	RestTemplate template;

	String urlExternalService = "https://my.api.mockaroo.com/test-tecnico/search";

	@GetMapping(value="code/{param}",produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Consumo servicio externo", response = BaseResponseDto.class)
	public Object ExternalService(@PathVariable("param") String param) {
		String encryptedParam = param;
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-api-key", "f2f719e0");
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		try{
		ResponseEntity<Object> response = template.exchange(
				urlExternalService+"/"+encryptedParam, HttpMethod.GET, requestEntity, Object.class);
		return response.getBody();}
		catch(Exception e){
			return "error"+e;
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creacion de usuario", response = BaseResponseDto.class)
	public BaseResponseDto UserCreate(@RequestBody UserDto User) {
			if(baseService.postUserCreate(EntityUtilities.copyObjectFrom(User, UserBo.class))){
			return new BaseResponseDto<>(ResultCodeType.SUCCESS, MessageSourceUtilities.getValue(Constant.MSGE_SUCCESS_GENERIC_OPERATION));}
			else{
				return new BaseResponseDto<>(ResultCodeType.ERROR, MessageSourceUtilities.getValue(Constant.MSGE_ERROR_USER_EXISTS));
			}
		}

	@GetMapping(value="users",produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Lista de Usuarios", response = BaseResponseDto.class)
	public BaseResponseDto UsersList() {
		List<UserDto> userDtoList = new ArrayList<>();
		for (UserBo userBo: baseService.getUserList()) {
			userDtoList.add(EntityUtilities.copyObjectFrom(userBo, UserDto.class));
		}
		return new BaseResponseDto<>(ResultCodeType.SUCCESS, MessageSourceUtilities.getValue(Constant.MSGE_SUCCESS_GENERIC_OPERATION),userDtoList);
	}

	@DeleteMapping(value="{email}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Eliminar usuario por el email", response = BaseResponseDto.class)
	public BaseResponseDto DeleteUserByEmail(@PathVariable("email") String email) {
		if(baseService.deleteUserByEmail(email)){
			return new BaseResponseDto<>(ResultCodeType.SUCCESS, MessageSourceUtilities.getValue(Constant.MSGE_SUCCESS_GENERIC_OPERATION));}
		else{
			return new BaseResponseDto<>(ResultCodeType.ERROR, MessageSourceUtilities.getValue(Constant.MSGE_ERROR_USER_NOT_EXISTS));
		}
	}

	@GetMapping(value="{email}",produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Buscar usuario por el email", response = BaseResponseDto.class)
	public BaseResponseDto FindUserByEmail(@PathVariable("email") String email) {
		try{
			return new BaseResponseDto<>(ResultCodeType.SUCCESS, MessageSourceUtilities.getValue(Constant.MSGE_SUCCESS_GENERIC_OPERATION),EntityUtilities.copyObjectFrom(baseService.getUserByEmail(email), UserDto.class));}
		catch(Exception e){
			return new BaseResponseDto<>(ResultCodeType.ERROR, MessageSourceUtilities.getValue(Constant.MSGE_ERROR_GENERIC_RESPONSE));
		}
	}

}
