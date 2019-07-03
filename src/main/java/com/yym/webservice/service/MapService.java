package com.yym.webservice.service;

import com.yym.webservice.model.KakaoMap;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface MapService {
  ResponseEntity<KakaoMap> findMapForKeyword(HashMap<String, String> paramMap);
}
