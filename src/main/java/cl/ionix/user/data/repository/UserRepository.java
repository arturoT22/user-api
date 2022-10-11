package cl.ionix.user.data.repository;
import cl.ionix.user.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByEmail(String email);

    @Transactional
    @Modifying
    @Query("Delete from UserEntity c Where c.email=?1")
    void deleteByEmail(String email);
}

