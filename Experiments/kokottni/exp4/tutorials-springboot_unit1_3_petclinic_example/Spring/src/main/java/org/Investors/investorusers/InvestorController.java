package org.Investors.investorusers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class InvestorController {

    @Autowired
    InvestorRepository investorRepository;

    private final Logger logger = LoggerFactory.getLogger(InvestorController.class);

    @RequestMapping(method = RequestMethod.POST, path = "/investors/new")
    public String saveInvestor(Investors investor){
        investorRepository.save(investor);
        return "New Investor! The next Warren Buffett goes by " + investor.getFirstName() + ". All hail this king";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/investor/creation")
    public String createMostFinanciallyStableMenInWorld(){
        Investors i1 = new Investors(1, "Warren", "Buffett", "haha@gotyamoney.com", 1234567890, "Bank of America, Apple, Coca-Cola");
        Investors i2 = new Investors(2, "Elon", "Musk", "emusk@spacetesla.org", 400000000, "Tesla, SpaceX, NASA");
        Investors i3 = new Investors(3, "Mark", "Zuckerberg", "mark@facebook.com", 989766575, "Facebook, Instagram, YouTube");
        Investors i4 = new Investors(4, "Nick", "Kokott", "koko@hotmail.com", 987396786, "SpaceX, IBM, Intel");
        investorRepository.save(i1);
        investorRepository.save(i2);
        investorRepository.save(i3);
        investorRepository.save(i4);
        return "Created top tier chumps";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/investors")
    public List<Investors> getAllInvestors(){
        logger.info("Entered into Controller Layer");
        List<Investors> results = investorRepository.findAll();
        logger.info("Number of Investors Found: " + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/investors/{id}")
    public Optional<Investors> findInvestorById(@PathVariable("id") int id){
        logger.info("Entered into Controller Layer");
        Optional<Investors> results = investorRepository.findById(id);
        return results;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/owners/{ownerId}")
    public Optional<Investors> deleteOwnerById(@PathVariable("id") int id){
        logger.info("Entered into Controller Layer");
        Optional<Investors> deleted = investorRepository.findById(id);
        investorRepository.deleteById(id);
        return deleted;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/owners/{ownerId}")
    public Optional<Investors> updateOwner(@PathVariable("id") int id){
        investorRepository.getOne(id).setFirstName("Shawty");
        Optional<Investors> changed = investorRepository.findById(1);
        return changed;
    }

}
