package cl.ionix.user.core.services.impl;

import cl.ionix.user.core.bo.UserBo;
import cl.ionix.user.core.services.UserService;
import cl.ionix.user.data.entity.UserEntity;
import cl.ionix.user.data.repository.UserRepository;
import cl.ionix.user.util.EntityUtilities;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository users;


	@Override
	public List<UserBo> getUserList() {
		List<UserBo> userBoList = new ArrayList<>();
		for (UserEntity userEntity:users.findAll()) {
			userBoList.add(EntityUtilities.copyObjectFrom(userEntity, UserBo.class));
		}
		return userBoList;

	}

	@Override
	public boolean postUserCreate(UserBo User) {
		if(users.findByEmail(User.getEmail()) != null){
			return false;
		}
		else{
		UserEntity userEntity = EntityUtilities.copyObjectFrom(User,UserEntity.class );
		users.save(userEntity);
		return true;
		}
	}

	@Override
	public boolean deleteUserByEmail(String email){

		if(users.findByEmail(email) != null){
			users.deleteByEmail(email);
			return true;
		}
		else{
			return false;
		}
	}


	@Override
	public UserBo getUserByEmail(String email) {
		return EntityUtilities.copyObjectFrom(users.findByEmail(email), UserBo.class);
	}
}
