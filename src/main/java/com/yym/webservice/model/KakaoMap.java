package com.yym.webservice.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class KakaoMap {
  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private KakaoMeta meta;

  @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name="meta_id")
  private List<Document> documents;

}
