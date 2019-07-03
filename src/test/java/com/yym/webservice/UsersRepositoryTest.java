package com.yym.webservice;

import com.yym.webservice.model.History;
import com.yym.webservice.model.Users;
import com.yym.webservice.repository.UsersRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Value("${initialUsername}")
  String username;

  @Test
  public void 저장되어있는_유저_불러오기() {
    Users getUser = usersRepository.findByUsername(username);
    Assert.assertEquals(username, getUser.getUsername());
  }

  @Test
  public void 유저_저장하고_불러오기() {
    Users user = new Users("test1",passwordEncoder.encode("pass"));
    usersRepository.save(user);

    Users getUser = usersRepository.findByUsername(user.getUsername());
    Assert.assertEquals("test1", getUser.getUsername());
  }

  @Test
  public void 히스토리_저장하고_불러오고_카운팅() {
    usersRepository.save(Users.builder()
            .password("123")
            .username("test")
            .build());
    Users user = usersRepository.findByUsername("test");

    //카카오 키워드 검색 히스토리 객체 추가
    user.addHistory(History.builder().keyword("카카오").build());
    usersRepository.save(user);
    Assert.assertEquals(1, user.getHistory().size());

    //카카오뱅크 키워드 검색 히스토리 객체 추가
    user.addHistory(History.builder().keyword("카카오뱅크").build());
    usersRepository.save(user);
    Assert.assertEquals(2, user.getHistory().size());
  }
}
