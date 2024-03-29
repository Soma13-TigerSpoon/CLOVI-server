package com.clovi.app.timeframe;

import com.clovi.app.timeShopItem.TimeShopItem;
import com.clovi.app.base.domain.BaseEntity;
import com.clovi.app.model.Model;
import com.clovi.app.timeframe.dto.request.TimeframeCreateRequest;
import com.clovi.app.timeframe.dto.request.TimeframeUpdateRequest;
import com.clovi.app.video.Video;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {
    @Index(name = "i_start_time", columnList = "capturePoint"),
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timeframe extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long capturePoint;

  @ManyToOne(fetch = FetchType.LAZY)
  private Video video;


  @OneToMany(mappedBy = "time", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<TimeShopItem> items = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  private Model model;


  @Builder
  public Timeframe(Long capturePoint, Model model, Video video) {
    this.capturePoint = capturePoint;
    this.model = model;
    this.video = video;
  }

  public Timeframe(TimeframeCreateRequest timeFrameCreateRequest, Video video, Long id) {
    this.capturePoint = timeFrameCreateRequest.getTime();
    this.video = video;
    this.createBy = id;
    this.lastModifiedBy = id;
  }
  public void update(TimeframeUpdateRequest timeFrameUpdateRequest, Long id){
    this.capturePoint = timeFrameUpdateRequest.getTime();
    this.lastModifiedBy = id;
  }

  public void addItem(TimeShopItem timeShopItem) {
    this.items.add(timeShopItem);
  }

}
