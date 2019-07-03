package com.yym.webservice.service;

import com.yym.webservice.model.History;
import com.yym.webservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

  @Autowired
  HistoryRepository historyRepository;

  @Override
  public List<Object[]> fetchMostKeywords(Pageable pageable) {
    return historyRepository.fetchMostKeywords(pageable);
  }

  @Override
  public List<History> findByUserIdOrderByIdDesc(Long user_id) {
    return historyRepository.findByUserIdOrderByIdDesc(user_id);
  }
}
