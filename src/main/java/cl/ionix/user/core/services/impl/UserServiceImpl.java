package cl.ionix.user.core.services.impl;

import cl.ionix.user.core.bo.UserBo;
import cl.ionix.user.core.services.UserService;
import cl.ionix.user.data.entity.UserEntity;
import cl.ionix.user.data.repository.UserRepository;
import cl.ionix.user.exception.UserFoundException;
import cl.ionix.user.exception.UserNotFoundException;
import cl.ionix.user.exception.UsersDatabaseIsEmptyException;
import cl.ionix.user.util.EntityUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository users;


	@Override
	public List<UserBo> getUserList() throws UsersDatabaseIsEmptyException {
		if(users.findAll() == null){
			throw new UsersDatabaseIsEmptyException("User database is empty");
		}

		List<UserBo> userBoList = new ArrayList<>();
		for (UserEntity userEntity:users.findAll()) {
			userBoList.add(EntityUtilities.copyObjectFrom(userEntity, UserBo.class));
		}
		return userBoList;

	}

	@Override
	public void postUserCreate(UserBo User) throws UserFoundException {
		if(users.findByEmail(User.getEmail()) != null){
			throw new UserFoundException("User ["+User.getEmail()+"] not found");
		}
		UserEntity userEntity = EntityUtilities.copyObjectFrom(User,UserEntity.class );
		users.save(userEntity);
	}

	@Override
	public void deleteUserByEmail(String email) throws UserNotFoundException {

		UserEntity byEmail = users.findByEmail(email);
		if(byEmail == null){
			throw new UserNotFoundException("User ["+email+"] not found");
		}
		users.deleteByEmail(email);
	}


	@Override
	public UserBo getUserByEmail(String email) throws UserNotFoundException {
		UserEntity byEmail = users.findByEmail(email);
		if(byEmail == null){
			throw new UserNotFoundException("User ["+email+"] not found");
		}
		return EntityUtilities.copyObjectFrom(users.findByEmail(email), UserBo.class);
	}
}
