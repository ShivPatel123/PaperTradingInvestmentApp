package org.springframework.samples.petclinic.system;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome</br> Go to localhost:8080/owners/create to create dummy data </br>";
    }

    @GetMapping("/iamhimothy")
    public String secretWelcome(){
        int i = 24;
        int dinero = i * 10000000;
        return "You now have all the money in the world hehehe $" + dinero;
    }
}
