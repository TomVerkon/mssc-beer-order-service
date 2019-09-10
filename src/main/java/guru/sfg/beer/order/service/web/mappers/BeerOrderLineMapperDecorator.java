package guru.sfg.beer.order.service.web.mappers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.beer.BeerService;
import guru.sfg.beer.order.service.services.beer.model.BeerDto;
import guru.sfg.beer.order.service.web.model.BeerOrderLineDto;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    private BeerService beerService;
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
	this.beerService = beerService;
    }

    @Autowired
    public void setMapper(BeerOrderLineMapper beerOrderLineMapper) {
	this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
	BeerOrderLineDto beerOrderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
	Optional<BeerDto> optionalBeerDto = beerService.getBeerByUpc(line.getUpc());
	if (optionalBeerDto.isPresent()) {
	    BeerDto beerDto = optionalBeerDto.get();
	    beerOrderLineDto.setBeerId(beerDto.getId());
	    beerOrderLineDto.setBeerName(beerDto.getBeerName());
	    beerOrderLineDto.setBeerStyle(beerDto.getBeerStyle());
	    beerOrderLineDto.setPrice(beerDto.getPrice());
	}

	return beerOrderLineDto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
	return beerOrderLineMapper.dtoToBeerOrderLine(dto);
    }

}
