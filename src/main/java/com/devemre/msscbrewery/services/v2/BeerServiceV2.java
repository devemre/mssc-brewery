package com.devemre.msscbrewery.services.v2;

import com.devemre.msscbrewery.web.model.BeerDto;
import com.devemre.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 saveNewBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDtoV2 beerDto);

    void deleteBeer(UUID beerId);
}
