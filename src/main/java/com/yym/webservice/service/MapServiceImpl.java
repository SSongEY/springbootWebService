package com.yym.webservice.service;

import com.yym.webservice.model.KakaoMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
public class MapServiceImpl implements MapService{

  private RestTemplate restTemplate;
  private HttpHeaders httpHeaders;
  @Value("${kakao.keywordSearchUrl}")
  private String mapApiUrl;
  @Value("${kakao.apikey}")
  private String apiKey;

  @PostConstruct
  private void init(){
    restTemplate = new RestTemplate();
    // 헤더정보세팅
    httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.AUTHORIZATION, apiKey);
  }

  @Override
  public ResponseEntity<KakaoMap> findMapForKeyword(HashMap<String, String> paramMap) {
    String keyword = paramMap.get("keyword");
    String page = paramMap.get("page");
    String pageLength = paramMap.get("pageLength");
    String makeUrl = String.format(mapApiUrl,keyword,page,pageLength);

    ResponseEntity<KakaoMap> response = restTemplate.exchange(makeUrl, HttpMethod.GET, new HttpEntity(httpHeaders), KakaoMap.class);
    return response;
  }
}
