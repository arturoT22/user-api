package cl.ionix.user.core.services;
import cl.ionix.user.controller.dto.base.UserDto;
import cl.ionix.user.core.bo.UserBo;
import cl.ionix.user.exception.UserFoundException;
import cl.ionix.user.exception.UserNotFoundException;
import cl.ionix.user.exception.UsersDatabaseIsEmptyException;


import java.util.List;

public interface UserService {

	void postUserCreate(UserBo User) throws UserFoundException;

	List<UserBo> getUserList() throws UsersDatabaseIsEmptyException;

	void deleteUserByEmail(String email) throws UserNotFoundException;

	UserBo getUserByEmail(String email) throws UserNotFoundException;
}
