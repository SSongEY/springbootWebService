package com.yym.webservice.service;

import com.yym.webservice.model.History;
import com.yym.webservice.model.Users;
import com.yym.webservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UsersService implements UserDetailsService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private HistoryService historyService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Value("${initialUsername}")
  String username;

  @Value("${initialPassword}")
  String password;

  @PostConstruct
  private void insertUsersBeforeInit(){
    usersRepository.save(Users.builder()
            .password(passwordEncoder.encode(password))
            .username(username)
            .build());

    IntStream.range(1,3).forEach(i -> {
      usersRepository.save(Users.builder()
              .password(passwordEncoder.encode(password))
              .username(username+i)
              .build());
    });
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usersRepository.findByUsername(username);
  }

  public void addKeywordHistory(String username, String keyword){
    Users getUser = usersRepository.findByUsername(username);
    getUser.addHistory(History.builder().keyword(keyword).build());
    usersRepository.save(getUser);
  }

  public List<History> fetchMyHistoryListDesc(String username){
    List<History> result;

    Users getUser = usersRepository.findByUsername(username);
    result = historyService.findByUserIdOrderByIdDesc(getUser.getId())
            .stream()
            .filter(distinctByKey(h -> h.getKeyword()))
            .collect(Collectors.toList());

    return result;
  }

  public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

}
