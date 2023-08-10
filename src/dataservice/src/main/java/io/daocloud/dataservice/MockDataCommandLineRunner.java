package io.daocloud.dataservice;

import io.daocloud.dataservice.entity.Ad;
import io.daocloud.dataservice.repository.AdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
class MockDataCommandLineRunner implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(MockDataCommandLineRunner.class);

    private final AdRepository adRepository;

    public MockDataCommandLineRunner(final AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Ad ad = new Ad();
        ad.setAdKey("binoculars");
        ad.setRedirectURL("/product/2ZYFJ3GM2N");
        ad.setContent("Roof Binoculars for sale. 50% off.");
        adRepository.save(ad);

        ad = new Ad();
        ad.setAdKey("telescopes");
        ad.setRedirectURL("/product/66VCHSJNUP");
        ad.setContent("Starsense Explorer Refractor Telescope for sale. 20% off.");
        adRepository.save(ad);

        ad = new Ad();
        ad.setAdKey("accessories");
        ad.setRedirectURL("/product/0PUK6V6EV0");
        ad.setContent("Solar System Color Imager for sale. 30% off.");
        adRepository.save(ad);

        ad = new Ad();
        ad.setAdKey("accessories");
        ad.setRedirectURL("/product/6E92ZMYYFZ");
        ad.setContent("Solar Filter for sale. Buy two, get third one for free");
        adRepository.save(ad);

        ad = new Ad();
        ad.setAdKey("accessories");
        ad.setRedirectURL("/product/L9ECAV7KIM");
        ad.setContent("Lens Cleaning Kit for sale. Buy one, get second one for free");
        adRepository.save(ad);

        ad = new Ad();
        ad.setAdKey("assembly");
        ad.setRedirectURL("/product/9SIQT8TOJO");
        ad.setContent("Optical Tube Assembly for sale. 10% off.");
        adRepository.save(ad);

        ad = new Ad();
        ad.setAdKey("travel");
        ad.setRedirectURL("/product/1YMWWN1N4O");
        ad.setContent("Eclipsmart Travel Refractor Telescope for sale. Buy one, get second kit for free");
        adRepository.save(ad);

        logger.info("init data success.");
    }
}