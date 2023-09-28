package io.daocloud.dataservice.controller;

import io.daocloud.dataservice.entity.Ad;
import io.daocloud.dataservice.entity.Info;
import io.daocloud.dataservice.repository.AdRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/ad") // This means URL's start with /ad (after Application path)
public class DataserviceController {

    private static final Logger logger = LogManager.getLogger(DataserviceController.class);
    private AdRepository adRepository;

    public DataserviceController(final AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser(@RequestParam String redirectURL, @RequestParam String content) {
        Ad n = new Ad();
        n.setRedirectURL(redirectURL);
        n.setContent(content);
        adRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<Ad> getAllAds() {
        // This returns a JSON or XML with the users
        return adRepository.findAll();
    }

    @GetMapping(path = "/id/{id}")
    @ResponseBody
    public Ad getAdById(@PathVariable("id") int id) {
        // This returns a JSON or XML with the users
        return adRepository.findById(id).orElse(new Ad());
    }

    @GetMapping(path = "/ad-key/{adKey}")
    @ResponseBody
    public Iterable<Ad> findAdvertiseByAdKey(@PathVariable("adKey") String adKey) {
        // This returns a JSON or XML with the users
        logger.info("received ad request (context_words=" + adKey + ")");
        return adRepository.findByAdKey(adKey);
    }

    @GetMapping(path = "/call")
    @ResponseBody
    public Info call() {
        Random random = new Random();
        Info info = new Info();
        info.setID(String.format("%d%d", System.currentTimeMillis(), random.nextInt(10)));
        info.setHostName(System.getenv("HOSTNAME"));
        info.setVersion(System.getenv("VERSION"));
        return info;
    }
}
