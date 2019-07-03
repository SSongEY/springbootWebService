package com.yym.webservice.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/mapPage")
public class MapController {

  @GetMapping
  public String main() {
    return "map";
  }
}
