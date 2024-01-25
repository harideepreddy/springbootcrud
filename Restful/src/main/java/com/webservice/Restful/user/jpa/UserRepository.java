package com.webservice.Restful.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservice.Restful.user.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
