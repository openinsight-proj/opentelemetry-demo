package io.daocloud.dataservice.controller;

import io.daocloud.dataservice.entity.Advertise;
import io.daocloud.dataservice.repository.AdvertiseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/ad") // This means URL's start with /ad (after Application path)
public class AdvertiseController {

    private static final Logger logger = LogManager.getLogger(AdvertiseController.class);
    private AdvertiseRepository advertiseRepository;

    public AdvertiseController(final AdvertiseRepository advertiseRepository) {
        this.advertiseRepository = advertiseRepository;
    }

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser(@RequestParam String redirectURL, @RequestParam String content) {
        Advertise n = new Advertise();
        n.setRedirectURL(redirectURL);
        n.setContent(content);
        advertiseRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<Advertise> getAllAds() {
        // This returns a JSON or XML with the users
        return advertiseRepository.findAll();
    }

    @GetMapping(path = "/id/{id}")
    @ResponseBody
    public Advertise getAdById(@PathVariable("id") int id) {
        // This returns a JSON or XML with the users
        return advertiseRepository.findById(id).orElse(new Advertise());
    }

    @GetMapping(path = "/ad-key/{adKey}")
    @ResponseBody
    public Iterable<Advertise> findAdvertiseByAdKey(@PathVariable("adKey") String adKey){
        // This returns a JSON or XML with the users
        logger.info("received ad request (context_words=" + adKey + ")");
        return advertiseRepository.findByAdKey(adKey);
    }
}