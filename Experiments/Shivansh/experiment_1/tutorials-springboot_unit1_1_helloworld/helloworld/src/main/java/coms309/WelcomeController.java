package coms309;

import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
class Person{
    String firstName;
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Person(){
        firstName = "Shiv" + Math.random();
    }
}

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello and welcome to COMS 309";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello and welcome to COMS 309: " + name;
    }

    @GetMapping("/people")
    public @ResponseBody String createPerson(@RequestBody Person person){
        JSONObject peopleList = new JSONObject();
        System.out.println(person.toString());
        peopleList.put(person.getFirstName(), person);
        return "New person" + person.getFirstName() + " Saved";
    }

    @GetMapping("/people/{firstName}")
    public @ResponseBody Person getPerson(@PathVariable String firstName){
        return peopleList.get(firstName);
    }
}
