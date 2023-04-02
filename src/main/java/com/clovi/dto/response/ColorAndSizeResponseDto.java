package com.clovi.dto.response;

import com.clovi.domain.item.Item;
import lombok.Getter;

@Getter
public class ColorAndSizeResponseDto {
    private String color;
    private String size;

    public ColorAndSizeResponseDto(String color, String size) {
        this.color = color;
        this.size = size;
    }
    public ColorAndSizeResponseDto(Item item) {
        this.color = item.getColor();
        this.size = item.getSize();
    }
}