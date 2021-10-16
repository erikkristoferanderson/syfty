package net.syfty.app.database.repository;

import net.syfty.app.database.entity.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    List<UserRole> findByUserId(Integer userId);
}
