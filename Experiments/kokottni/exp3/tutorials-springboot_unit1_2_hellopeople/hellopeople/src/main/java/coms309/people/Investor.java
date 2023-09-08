package coms309.people;

public class Investor extends Person{

    private int money;

    private String interestedCompanies;

    private int netWorth;


    public Investor(){

    }

    public Investor(String firstName, String lastName, String address, String telephone, int money, String interestedCompanies, int netWorth){
        super(firstName, lastName, address, telephone);
        this.money = money;
        this.interestedCompanies = interestedCompanies;
        this.netWorth = netWorth;
    }

    public int getMoney(){return this.money;}

    public void setMoney(int money){this.money = money;}

    public String getInterestedCompanies(){return this.interestedCompanies;}

    public void setInterestedCompanies(String interestedCompanies){this.interestedCompanies = interestedCompanies;}

    public int getNetWorth(){return this.netWorth;}

    public void setNetWorth(int netWorth){this.netWorth = netWorth;}

    @Override
    public String toString(){
        return super.toString() + " " + money + " " + interestedCompanies + " " + netWorth;
    }

}
