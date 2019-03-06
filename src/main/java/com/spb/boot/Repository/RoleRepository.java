package com.spb.boot.Repository;

import com.spb.boot.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Role,Integer> ,JpaSpecificationExecutor<Role> {

    public Role findByRolename(String rolename);
}
