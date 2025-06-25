package org.daocloud.springcloud.adservice.controller;

import com.alibaba.nacos.common.utils.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.daocloud.springcloud.adservice.model.Cookie;
import org.daocloud.springcloud.adservice.service.AdServiceGrpcService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RefreshScope
public class HelloWorld {
    private static final Logger logger = LogManager.getLogger(AdServiceGrpcService.class);
    private final Environment environment;

    private static AtomicLong count = new AtomicLong();

    private static Long limit = 1L;

    @Value("${test.config:default}")
    private String config;

    @Value("${spring.dataService.enabled}")
    private boolean dataServiceEnabled;

    public HelloWorld(Environment environment) {
        this.environment = environment;
    }

    @RequestMapping("/")
    public Mono<String> helloWorld() {
        logger.info("adservice-springcloud: hello world!");
        return Mono.just("adservice-springcloud: hello world!");
    }

    @RequestMapping("/test1")
    public Mono<String> test1() {
        logger.info("This is a test 1 API.");
        return Mono.just("This is a test 1 API.");
    }

    @RequestMapping("/test2")
    public Mono<String> test2() {
        logger.info("This is a test 2 API.");
        return Mono.just("This is a test 2 API.");
    }

    @GetMapping("/dynamic-config")
    public Mono<String> dynamicConfig() {
        return Mono.just(config);
    }

    @RequestMapping("/timeout/{timeout}")
    public Mono<String> helloWorld(@PathVariable long timeout) throws InterruptedException {
        Thread.sleep(timeout);
        logger.info("timeout:" + timeout);
        return Mono.just("timeout:" + timeout);
    }

//    @PostMapping("/method")
//    public Mono<String> MethodPost(){
//        return Mono.just("method:"+)
//    }

    @RequestMapping("/method")
    public Mono<String> method(ServerHttpRequest request) {
        logger.info("method:" + request.getMethodValue());
        return Mono.just("method:" + request.getMethodValue());
    }

    @RequestMapping("/hostname")
    public Mono<String> hostname() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostName = localHost.getHostName();
        logger.info("hostname:" + hostName);

        return Mono.just("hostname:" + hostName);
    }

    @RequestMapping("/ip")
    public Mono<String> ip() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        String address = localHost.getHostAddress();
        logger.info("ip address:" + address);
        return Mono.just("ip address:" + address);
    }


    @RequestMapping({"/path/**", "/path**"})
    public Mono<String> path(ServerHttpRequest request) {
        logger.info("path:" + request.getPath());
        return Mono.just("path:" + request.getPath());
    }

    @RequestMapping("/set-retry-count/{limit}")
    public Mono<String> retryCount(@PathVariable long limit) {
        HelloWorld.limit = limit;
        count = new AtomicLong();
        logger.info("retry-count-limit:" + limit);
        return Mono.just("retry-count-limit:" + limit);
    }

    @RequestMapping("/retry")
    public Mono<String> retry(ServerHttpResponse response) {
        if (limit < 1 || count.addAndGet(1) % limit == 0) {
            count = new AtomicLong();
            return Mono.just("retry:" + "success");
        }
        response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        logger.info("retry fail,count is:" + count.get());
        return Mono.just("retry fail,count is:" + count.get());
    }

    @RequestMapping("/request-header")
    public Mono<Map<String, List<String>>> requestHeader(ServerHttpRequest request, @RequestParam(required = false) List<String> header) {
        if (CollectionUtils.isEmpty(header)) {
            return Mono.empty();
        }
        Map<String, List<String>> headers = new HashMap<>();
        for (String hea : header) {
            headers.put(hea, request.getHeaders().get(hea));
        }
        return Mono.just(headers);
    }

    @RequestMapping("/response-header")
    public Mono<Map<String, List<String>>> responseHeader(ServerHttpResponse response, @RequestParam(required = false) List<String> header) {
        if (CollectionUtils.isEmpty(header)) {
            return Mono.empty();
        }
        Map<String, List<String>> headers = new HashMap<>();
        for (String hea : header) {
            headers.put(hea, response.getHeaders().get(hea));
        }
        return Mono.just(headers);
    }

    @RequestMapping("/cookie-set")
    public Mono<String> cookieSet(ServerHttpResponse response, Cookie cookie) {
        if (cookie == null) {
            return Mono.empty();
        }
        ResponseCookie responseCookie = ResponseCookie.from(cookie.getName(), cookie.getValue())
                .domain(cookie.getDomain())
                .httpOnly(cookie.isHttpOnly())
                .maxAge(cookie.getMaxAge())
                .path(cookie.getPath())
                .sameSite(cookie.getSameSite())
                .secure(cookie.isSecure())
                .build();
        response.addCookie(responseCookie);
        return Mono.just(cookie.toString());
    }

    @GetMapping("/status/{statusCode}")
    public Mono<String> getHttpStatus(ServerHttpResponse response, @PathVariable String statusCode) {
        response.setStatusCode(HttpStatus.valueOf(Integer.parseInt(statusCode)));
        logger.info("status:" + statusCode);
        return Mono.just("ok");
    }

    @RequestMapping("/healthz")
    public Mono<String> healthz() throws UnknownHostException {
        return Mono.just("ok");
    }

    @RequestMapping(value = "return-body", method = RequestMethod.POST)
    public Mono<String> returnBody(@RequestBody String body) {
        return Mono.just(body);
    }

    @RequestMapping("/custom-return-size")
    public Mono<String> customReturnSize(@RequestParam(required = false,defaultValue = "10") Long size){
        char[] chars = new char[size.intValue()];
        Arrays.fill(chars, 'a');
        return Mono.just(new String(chars));
    }

    @RequestMapping({"/dataservice/**"})
    public Mono<String> dataservice(ServerHttpRequest request) {
        logger.info("Original path: " + request.getPath());
        if (!dataServiceEnabled) {
            return Mono.just("Data service is disabled.");
        }

        String dataServiceAddr = System.getenv("DATA_SERVICE_ADDR");
        if (dataServiceAddr == null || dataServiceAddr.isEmpty()) {
            return Mono.just("DATA_SERVICE_ADDR is not set.");
        }

        String path = request.getPath().value();
        String actualPath = path.replaceFirst("/dataservice", "/ad");
        String targetUrl = "http://" + dataServiceAddr + actualPath;
        logger.info("Forwarding to: " + targetUrl);

        WebClient webClient = WebClient.builder().build();
        return webClient.get()
                .uri(targetUrl)
                .retrieve()
                .bodyToMono(String.class);
    }
}
