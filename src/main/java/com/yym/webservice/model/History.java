package com.yym.webservice.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class History extends BaseTimeEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String keyword;

  @Column
  private Long userId;

  @Builder
  public History(String keyword){
    this.keyword = keyword;
  }

  @Override
  public boolean equals(Object obj) {
    System.out.println(this.getKeyword() + "       " + ((History)obj).getKeyword());
    return this.getKeyword().equals(((History)obj).getKeyword());
  }
}
