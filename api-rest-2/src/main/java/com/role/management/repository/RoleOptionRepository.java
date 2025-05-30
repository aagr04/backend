package com.role.management.repository;

import com.role.management.entity.RoleOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleOptionRepository extends JpaRepository<RoleOption, Integer> {
}
