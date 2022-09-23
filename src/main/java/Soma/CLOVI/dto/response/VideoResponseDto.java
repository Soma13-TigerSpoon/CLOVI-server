package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.youtube.Video;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class VideoResponseDto {

  private Long id;
  private String creator;

  private String profileImgUrl;

  private List<TimeShopItemResponseDto> lists = new ArrayList<>();

  public VideoResponseDto(Video video) {
    this.id = video.getId();
    this.creator = video.getChannel().getName(); // Select Channel
    this.profileImgUrl = video.getChannel().getProfileImgUrl(); // Select Channel Image

    List<TimeFrame> timeFrames = video.getTimeFrames(); // Select TimeFrame
    if (!timeFrames.isEmpty()) {
      for (TimeFrame timeFrame : video.getTimeFrames()) {
        this.lists.add(new TimeShopItemResponseDto(timeFrame));
      }
    }
  }
}