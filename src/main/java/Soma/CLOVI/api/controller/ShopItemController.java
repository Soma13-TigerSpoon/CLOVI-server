package Soma.CLOVI.api.controller;

import Soma.CLOVI.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopItemController {
    private final ShopItemService shopItemService;


}