package com.yym.webservice.web.api;

import com.yym.webservice.model.KakaoMap;
import com.yym.webservice.service.MapService;
import com.yym.webservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;


@RestController
@RequestMapping(value = "/maps")
public class MapRestController {

  @Autowired
  MapService mapService;

  @Autowired
  UsersService usersService;

  @GetMapping("/")
  public ResponseEntity<KakaoMap> getMaps(@RequestParam HashMap<String, String> paramMap, Principal principal){
    String username = principal.getName();
    String keyword = paramMap.get("keyword");
    usersService.addKeywordHistory(username, keyword);

    return mapService.findMapForKeyword(paramMap);
  }
}
