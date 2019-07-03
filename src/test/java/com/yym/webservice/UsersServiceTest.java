package com.yym.webservice;

import com.yym.webservice.model.History;
import com.yym.webservice.model.Users;
import com.yym.webservice.repository.UsersRepository;
import com.yym.webservice.service.UsersService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

  @Autowired
  UsersService usersService;

  @Autowired
  UsersRepository usersRepository;

  @Test
  public void 키워드_최신_히스토리_중복제거후_가져오기() {
    Users user = usersRepository.findByUsername("testuser");
    user.addHistory(History.builder().keyword("카카오1").build());
    user.addHistory(History.builder().keyword("카카오1").build());
    user.addHistory(History.builder().keyword("카카오2").build());
    user.addHistory(History.builder().keyword("카카오2").build());
    user.addHistory(History.builder().keyword("카카오3").build());
    user.addHistory(History.builder().keyword("카카오3").build());
    user.addHistory(History.builder().keyword("카카오4").build());
    user.addHistory(History.builder().keyword("카카오4").build());
    usersRepository.save(user);

    List<History> fetchList = usersService.fetchMyHistoryListDesc("test").stream().collect(Collectors.toList());
    //8개 키워드 쌍으로 중복이므로, service에서 fetchlist의 사이즈는 4
    Assert.assertEquals(4, fetchList.size());
  }

}