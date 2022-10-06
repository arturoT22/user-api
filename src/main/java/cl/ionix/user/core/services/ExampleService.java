package cl.ionix.user.core.services;

import cl.ionix.user.core.bo.ResponseExampleBo;
import cl.ionix.user.core.bo.UserBo;
import cl.ionix.user.data.entity.UserEntity;

import java.util.List;

public interface ExampleService {
	ResponseExampleBo getExample();
	void postUserCreate(UserBo User);

	List<UserEntity> getUserList();
}
