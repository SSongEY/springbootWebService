package com.yym.webservice.web.api;

import com.yym.webservice.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/historys")
public class HistoryApiController {

  @Autowired
  HistoryService historyService;

  @GetMapping()
  public List<String> fetchMostKeywords(){
    return historyService.fetchMostKeywords(PageRequest.of(0, 10));
  }
}
