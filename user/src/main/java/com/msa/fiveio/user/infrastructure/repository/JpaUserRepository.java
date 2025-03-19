package com.msa.fiveio.user.infrastructure.repository;


import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.repository.UsersRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends UsersRepository, JpaRepository<Users, Long> {

}
