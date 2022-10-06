package cl.ionix.user.core.services.impl;

import cl.ionix.user.core.bo.ResponseExampleBo;
import cl.ionix.user.core.bo.UserBo;
import cl.ionix.user.core.services.ExampleService;
import cl.ionix.user.data.entity.UserEntity;
import cl.ionix.user.data.repository.UserJpaInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleServiceImpl implements ExampleService {

	@Autowired
	UserJpaInterface users;
	private final ObjectMapper objectMapper;

	public ExampleServiceImpl(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public ResponseExampleBo getExample() {
		ResponseExampleBo responseExampleBoo = new ResponseExampleBo();
		responseExampleBoo.setText("Texto de ejemplooooo");
		return responseExampleBoo;
	}

	@Override
	public List<UserEntity> getUserList() {

		return users.findAll();
	}

	@Override
	public void postUserCreate(UserBo Userr) {

		final UserEntity userEntity = objectMapper.convertValue(Userr, UserEntity.class);
		users.save(userEntity);

	}
}
