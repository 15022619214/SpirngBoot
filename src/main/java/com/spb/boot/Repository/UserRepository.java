package com.spb.boot.Repository;

import com.spb.boot.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

    public User findByUsername(String username);
}
