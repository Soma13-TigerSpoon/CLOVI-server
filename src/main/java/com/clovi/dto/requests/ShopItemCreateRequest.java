package com.clovi.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "쇼핑몰 생성 요청")
@NoArgsConstructor
public class ShopItemCreateRequest {

  @NotNull(message = "아이템 id는 필수 항목입니다!")
  @Schema(description = "아이템 id", example = "1")
  private Long itemId;

  @NotBlank(message = "호스트 이름은 필수 항목입니다!")
  @Schema(description = "host", example = "mss.kr")
  private String hostname;

  @NotBlank(message = "상품 구매 링크는 필수 항목입니다!")
  @Schema(description = "상품 구매 링크", example = "http://mss.kr/2970721")
  private String shopItemUrl;

  @NotBlank(message = "상품 가격은 필수 항목입니다!")
  @Schema(description = "상품 가격", example = "36000")
  private Long price;
}
