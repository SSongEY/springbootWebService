package com.yym.webservice.service;


import com.yym.webservice.model.History;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HistoryService {
  List<Object[]> fetchMostKeywords(Pageable pageable);
  List<History> findByUserIdOrderByIdDesc(Long user_id);
}
