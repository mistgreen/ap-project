package littlepartypro.repository;

import littlepartypro.model.UserEntity;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query("SELECT * FROM users WHERE username = :username")
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    Boolean existsByUsername(String username);

}