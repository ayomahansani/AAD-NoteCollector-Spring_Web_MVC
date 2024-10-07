package lk.ijse.notecollectorspringmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthCheckController {

    @GetMapping
    public String healthTest(){
        return "Note controller is working";
    }

}

// this class is defined for just test that whether working this application.
// normal GET request ekak dala text ekak return karanava
// me class eke eka endpoint ekai thiyenne