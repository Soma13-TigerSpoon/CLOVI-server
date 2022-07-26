package Soma.CLOVI.domain.item;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.ManyToMany.TimeItemAffiliationLink;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.domain.category.Category;
import Soma.CLOVI.dto.requests.TimeItemRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {
    @Index(name = "i_item_name", columnList = "name")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;
  private String color;
  private String size;

  private String brand;
  @Lob
  private String imgUrl;

  @Enumerated(EnumType.STRING)
  private FitStyle fitStyle;

  //=연관관계 매핑=//

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Item parent;
  @OneToOne(fetch = FetchType.LAZY)
  private Category category;

  @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
  private List<Item> childItems = new ArrayList<>();
  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<ShopItem> shopItems = new ArrayList<>();


  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<TimeItemAffiliationLink> times = new ArrayList<>();

  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<VideoItem> videoItems = new ArrayList<>();

  @Builder
  public Item(String name, String description, String color, String size, String imgUrl) {
    this.name = name;
    this.description = description;
    this.color = color;
    this.size = size;
    this.imgUrl = imgUrl;
  }

  public Item(TimeItemRequestDto timeItemRequestDto, Category category, Item parent) {
    this.name = timeItemRequestDto.getName();
    this.imgUrl = timeItemRequestDto.getItemImgUrl();
    this.color = timeItemRequestDto.getColor();
    this.size = timeItemRequestDto.getSize();
    this.brand = timeItemRequestDto.getBrand();
    this.fitStyle = timeItemRequestDto.isWide() ? FitStyle.와이드 : null;
    this.category = category;
    this.parent = parent;
  }

  public void addShopItem(ShopItem shopItem) {
    this.shopItems.add(shopItem);
  }

}
