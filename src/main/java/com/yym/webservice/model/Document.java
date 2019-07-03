package com.yym.webservice.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Document {
  @Id
  @GeneratedValue
  private Long id;
  @Column
  private String place_name;
  @Column
  private String category_name;
  @Column
  private String address_name;
  @Column
  private String distance;
  @Column
  private String road_address_name;
  @Column
  private String place_url;
  @Column
  private String phone;


}
