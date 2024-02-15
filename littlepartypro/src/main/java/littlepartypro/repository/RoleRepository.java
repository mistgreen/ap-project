package littlepartypro.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import littlepartypro.model.RoleEntity;

@Repository
public interface RoleRepository extends ListCrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleName(String name);
}
