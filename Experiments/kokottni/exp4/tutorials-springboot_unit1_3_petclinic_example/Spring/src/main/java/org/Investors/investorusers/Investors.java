package org.Investors.investorusers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;
@Entity
@Table(name = "investors")
public class Investors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "first_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String firstName;

    @Column(name = "last_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String lastName;

    @Column(name = "email")
    @NotFound(action = NotFoundAction.IGNORE)
    private String emailAddress;

    @Column(name = "email")
    @NotFound(action = NotFoundAction.IGNORE)
    private Double money;

    @Column(name = "companies")
    @NotFound(action = NotFoundAction.IGNORE)
    private String interestedCompanies;

    public Investors(){}

    public Investors(int id, String firstName, String lastName, String emailAddress, double money, String interestedCompanies){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.money = money;
        this.interestedCompanies = interestedCompanies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress(){return this.emailAddress;}

    public void setEmailAddress(String emailAddress){this.emailAddress = emailAddress;}

    public Double getMoney(){return this.money;}

    public void setMoney(Double money){this.money = money;}

    public String getInterestedCompanies(){return this.interestedCompanies;}

    public void setInterestedCompanies(String interestedCompanies){this.interestedCompanies = interestedCompanies;}

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("lastName", this.getLastName())
                .append("firstName", this.getFirstName())
                .append("email", this.emailAddress)
                .append("companies", this.interestedCompanies)
                .append("money", this.money).toString();

    }



}
