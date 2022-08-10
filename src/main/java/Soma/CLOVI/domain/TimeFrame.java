package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.item.Item;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_start_time", columnList = "startTime"),
        @Index(name = "i_end_time", columnList = "endTime")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeFrame extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long id;

    private Long startTime;
    private Long endTime;

    @Column(name = "video_id")
    private Long videoId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_id")
    private List<Item> itemList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;


    @Builder
    public TimeFrame(Long startTime, Long endTime, Model model){
        this.startTime = startTime;
        this.endTime = endTime;
        this.model = model;
    }

    public void addItem(Item item){
        this.itemList.add(item);
    }

}