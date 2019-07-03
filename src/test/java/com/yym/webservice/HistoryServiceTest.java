package com.yym.webservice;

import com.yym.webservice.model.History;
import com.yym.webservice.model.Users;
import com.yym.webservice.repository.UsersRepository;
import com.yym.webservice.service.HistoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryServiceTest {

  @Autowired
  HistoryService historyService;

  @Autowired
  UsersRepository usersRepository;

  @Test
  public void 유저별_키워드_최신_히스토리_중복제거후_가져오기() {
    Users user1 = usersRepository.findByUsername("testuser1");
    Users user2 = usersRepository.findByUsername("testuser2");
    Users user3 = usersRepository.findByUsername("testuser3");

    user1.addHistory(History.builder().keyword("카카오뱅크").build());
    user1.addHistory(History.builder().keyword("카카오").build());
    user1.addHistory(History.builder().keyword("카카오").build());
    user1.addHistory(History.builder().keyword("카카오").build());
    user1.addHistory(History.builder().keyword("카카오").build());
    user1.addHistory(History.builder().keyword("판교").build());

    user2.addHistory(History.builder().keyword("카카오뱅크").build());
    user2.addHistory(History.builder().keyword("카카오").build());
    user2.addHistory(History.builder().keyword("경기도").build());

    user3.addHistory(History.builder().keyword("카카오뱅크").build());
    user3.addHistory(History.builder().keyword("삼성동").build());
    user3.addHistory(History.builder().keyword("흑돈가").build());

    usersRepository.save(user1);
    usersRepository.save(user2);
    usersRepository.save(user3);

    //유저별 인기 검색 키워드 이기 때문에 유저별 키워드 중복제거 후 카운팅 이므로 1순위는 카카오뱅크
    List<String> list = historyService.fetchMostKeywords(PageRequest.of(0, 10));
    Assert.assertEquals("카카오뱅크", list.stream().findFirst().get());
  }
}