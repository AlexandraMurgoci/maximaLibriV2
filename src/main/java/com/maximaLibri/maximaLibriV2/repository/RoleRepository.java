package com.maximaLibri.maximaLibriV2.repository;

import com.maximaLibri.maximaLibriV2.model.Role;
import com.maximaLibri.maximaLibriV2.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}
