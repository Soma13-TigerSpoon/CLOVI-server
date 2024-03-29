package com.clovi.app.color.domain;

import com.clovi.app.itemInfo.ItemInfo;
import com.clovi.app.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemColor extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String imgUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  private Color color;

  private Long itemInfoId;

  public ItemColor(Long itemInfoId, Color color, String imgUrl, Long memberId) {
    this.imgUrl = imgUrl;
    this.color = color;
    this.itemInfoId = itemInfoId;
    this.createBy = memberId;
    this.lastModifiedBy = memberId;
  }
}
