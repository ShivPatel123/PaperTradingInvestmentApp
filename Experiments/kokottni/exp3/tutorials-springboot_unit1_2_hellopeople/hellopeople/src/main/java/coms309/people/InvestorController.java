package coms309.people;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.HashMap;

@RestController
public class InvestorController {

    HashMap<String, Investor> investorList = new HashMap<>();

    @GetMapping("/investors")
    public @ResponseBody HashMap<String,Investor> getAllPersons() {
        return investorList;
    }

    @PostMapping("/investors")
    public @ResponseBody String createPerson(@RequestBody Investor investor) {
        System.out.println(investor);
        investorList.put(investor.getFirstName(), investor);
        return "New person "+ investor.getFirstName() + " Saved";
    }

    @GetMapping("/investors/{firstName}")
    public @ResponseBody Investor getInvestor(@PathVariable String firstName) {
        Investor p = investorList.get(firstName);
        return p;
    }

    @PutMapping("/investors/{firstName}")
    public @ResponseBody Investor updateInvestor(@PathVariable String firstName, @RequestBody Investor p) {
        investorList.replace(firstName, p);
        return investorList.get(firstName);
    }

    @PutMapping("/investors/{firstname}/{money}")
    public @ResponseBody Investor updateInvestorMoney(@PathVariable String firstName, @PathVariable int money, @RequestBody Investor i){
        i.setMoney(money);
        investorList.replace(firstName, i);
        return investorList.get(firstName);
    }

    @DeleteMapping("/investors/{firstName}")
    public @ResponseBody HashMap<String, Investor> deletePerson(@PathVariable String firstName) {
        investorList.remove(firstName);
        return investorList;
    }

}
