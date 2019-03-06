package com.spb.boot.Service;

import com.spb.boot.Model.User;

public interface UserService {
    public void save(User user);
    public User findByUsername(String username);
}
