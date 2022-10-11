package cl.ionix.user.core.services;
import cl.ionix.user.core.bo.UserBo;


import java.util.List;

public interface UserService {

	boolean postUserCreate(UserBo User);

	List<UserBo> getUserList();

	boolean deleteUserByEmail(String email);

	UserBo getUserByEmail(String email);
}
