package com.yym.webservice.repository;

import com.yym.webservice.model.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
  List<History> findByUserIdOrderByIdDesc(Long user_id);
  @Query(value = "select keyword, count(keyword) as cnt from " +
          "(SELECT h.keyword keyword FROM HISTORY h group by h.user_id, h.keyword) " +
          "group by keyword order by cnt desc", nativeQuery = true)
  List<Object[]> fetchMostKeywords(Pageable pageable);
}
