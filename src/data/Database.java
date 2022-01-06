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

    /** getter for the number of years */
    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    /** setter for the number of years */
    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /** getter for Santa's budget */
    public Double getSantaBudget() {
        return santaBudget;
    }

    /** setter for Santa's budget */
    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    /** getter for the initial data */
    public InitialData getInitialData() {
        return initialData;
    }

    /** setter for the initial data */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /** getter for the list of children in the database */
    public ArrayList<Child> getChildren() {
        return children;
    }

    /** setter for the list of children in the database */
    public void setChildren(final ArrayList<Child> children) {
        this.children = children;
    }


    /** getter for the list of gifts in the database */
    public ArrayList<Gift> getGifts() {
        return gifts;
    }


    /** setter for the list of gifts in the database */
    public void setGifts(final ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }


    /** getter for the list of cities in the database */
    public ArrayList<Cities> getCities() {
        return cities;
    }


    /** setter for the list of cities in the database */
    public void setCities(final ArrayList<Cities> cities) {
        this.cities = cities;
    }


    /** getter for the list of annual changes */
    public ArrayList<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }


    /** getter for the list of annual changes */
    public void setAnnualChanges(final ArrayList<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
