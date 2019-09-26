package guru.sfg.beer.order.service.services.beer;

import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.sfg.beer.order.service.services.beer.model.BeerDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerServiceRestTemplateImpl implements BeerService {

    private final String BEER_PATH = "/api/v1/beer/{beerId}";
    private final String UPC_PATH = "/api/v1/beer/upc/{upc}";
    private final RestTemplate restTemplate;

    private String beerServiceHost;

    public void setBeerServiceHost(String beerServiceHost) {
	this.beerServiceHost = beerServiceHost;
    }

    public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
	this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
	log.debug("getBeerByUpc:" + upc);
	return Optional.of(restTemplate.getForObject(beerServiceHost + UPC_PATH, BeerDto.class, upc));
    }

    @Override
    public Optional<BeerDto> getBeerById(Long id) {
	log.debug("getBeerById:" + id.toString());
	return Optional.of(restTemplate.getForObject(beerServiceHost + BEER_PATH, BeerDto.class, id));
    }

}
