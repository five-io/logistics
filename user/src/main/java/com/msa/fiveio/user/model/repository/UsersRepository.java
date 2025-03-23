package com.msa.fiveio.user.model.repository;

import com.msa.fiveio.user.model.entity.Users;
import java.util.Optional;

public interface UsersRepository {

    Users save(Users users);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByUserId(Long userId);
}