package com.cbc.myblog.auth.repository;

import com.cbc.myblog.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cbc on 2018/4/1.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
