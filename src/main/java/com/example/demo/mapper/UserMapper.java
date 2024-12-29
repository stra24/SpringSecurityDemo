package com.example.demo.mapper;

import com.example.demo.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  List<User> findAll();

  User findByName(String name);

  int save(User user);

  int delete();
}
