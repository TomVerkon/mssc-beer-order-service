package guru.sfg.beer.order.service.services.beer;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.sfg.beer.order.service.bootstrap.BeerOrderBootStrap;
import guru.sfg.beer.order.service.services.beer.model.BeerDto;

@Disabled // utility for manual testing
@SpringBootTest
class BeerServiceRestTemplateImplTest {

    @Autowired
    BeerService beerService;

    @Test
    void testGetBeerByUpc() {
	Optional<BeerDto> optionalBeerDto = beerService.getBeerByUpc(BeerOrderBootStrap.BEER_1_UPC);
	assert (optionalBeerDto.isPresent());
	System.out.println(optionalBeerDto);
    }

    @Test
    void testGetBeerById() {
	Optional<BeerDto> optionalBeerDto = beerService
		.getBeerById(UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb"));
	assert (optionalBeerDto.isPresent());
	System.out.println(optionalBeerDto);
    }

}
