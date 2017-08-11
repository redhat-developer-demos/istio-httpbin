package com.redhat.developers.istio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static java.util.concurrent.TimeUnit.SECONDS;

@RestController
@Slf4j
public class HttpBinController {

    private RestTemplate restTemplate;

    public HttpBinController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Example of egress service
    @RequestMapping(path = "/httpbin/ip", method = RequestMethod.GET, produces = "application/json")
    public String orginIp(@Value("${httpbin.ip.url}") String endpointUrl) {
        log.info("Calling URL:{}", endpointUrl);
        String response = restTemplate.getForObject(endpointUrl, String.class);
        log.info("Response {}", response);
        return response;
    }

    //Example of egress service
    // I am not able to simulate the issue here as external service is closing the call or getting 504 from it after
    // certain number of requests wondering they have Circuit breaker in ;)
    @RequestMapping(path = "/httpbin/delay", method = RequestMethod.GET, produces = "application/json")
    public String delay2(@Value("${httpbin.delay.url}") String endpointUrl) {
        log.info("Calling URL:{} with delay of 10 secs", endpointUrl);
        log.info("External call started ...");
        String response = restTemplate.getForObject(endpointUrl + "/10", String.class);
        log.info("Response {}", response);
        return response;
    }


    @RequestMapping(path = "delay", method = RequestMethod.GET, produces = "text/plain")
    public String delay() {
        log.info("Delay call started ...");
        try {
            SECONDS.sleep(10);
        } catch (InterruptedException e) {
            //ignore
        }
        log.info("Delay call ended");
        return "I am after 10 secs";
    }
}
