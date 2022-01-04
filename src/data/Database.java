package data;

import enums.Cities;
import michelaneous.AnnualChange;
import michelaneous.Child;
import michelaneous.Gift;

import java.util.ArrayList;

public class Database {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private ArrayList<Child> children;
    private ArrayList<Gift> gifts;
    private ArrayList<Cities> cities;
    private ArrayList<AnnualChange> annualChanges;

    public Database() {
        this.numberOfYears = null;
        this.santaBudget = null;
        this.initialData = null;
        this.children = new ArrayList<>();
        this.gifts = new ArrayList<>();
        this.cities = new ArrayList<>();
        this.annualChanges = new ArrayList<>();
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(InitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    public ArrayList<Cities> getCities() {
        return cities;
    }

    public void setCities(ArrayList<Cities> cities) {
        this.cities = cities;
    }

    public ArrayList<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(ArrayList<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
