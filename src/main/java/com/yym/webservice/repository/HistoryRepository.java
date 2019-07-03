package com.yym.webservice.repository;

import com.yym.webservice.model.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
//  History findByUsername(String username);
  List<History> findByUserIdOrderByIdDesc(Long user_id);
  @Query(value = "select keyword from " +
          "(SELECT h.keyword keyword FROM HISTORY h group by h.user_id, h.keyword) " +
          "group by keyword order by count(keyword) desc", nativeQuery = true)
  List<String> fetchMostKeywords(Pageable pageable);
}
