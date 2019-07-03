package com.yym.webservice.web.api;

import com.yym.webservice.model.History;
import com.yym.webservice.service.UsersService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@NoArgsConstructor
public class UserApiController {

  @Autowired
  private UsersService usersService;

  @GetMapping("/history")
  public List<History> fetchMyHistoryList(Principal principal){
    return usersService.fetchMyHistoryListDesc(principal.getName());
  }
}
