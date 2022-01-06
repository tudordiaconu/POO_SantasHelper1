package michelaneous;

import enums.Category;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private String city;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private Double averageScore;
    private String ageCategory;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;
    private ArrayList<Category> receivedCategories;

    public Child() {
        this.id = -1;
        this.lastName = null;
        this.firstName = null;
        this.age = 0;
        this.city = null;
        this.niceScore = (double) 0;
        this.giftsPreferences = new ArrayList<>();
        this.averageScore = (double) 0;
        this.ageCategory = null;
        this.niceScoreHistory = new ArrayList<>();
        this.assignedBudget = (double) 0;
        this.receivedGifts = new ArrayList<>();
        this.receivedCategories = new ArrayList<>();
    }

    /** getter for the history of nice scores */
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /** setter for the history of nice scores */
    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /** getter for the list of received gifts */
    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /** setter for the list of received gifts */
    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /** getter for the list of the categories of the received gifts */
    public ArrayList<Category> getReceivedCategories() {
        return receivedCategories;
    }

    /** setter for the list of the categories of the received gifts */
    public void setReceivedCategories(final ArrayList<Category> receivedCategories) {
        this.receivedCategories = receivedCategories;
    }

    /** getter for assigned budget */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /** setter for assigned budget */
    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /** getter for the average score */
    public Double getAverageScore() {
        return averageScore;
    }

    /** setter for the average score */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    /** getter for the id */
    public Integer getId() {
        return id;
    }

    /** setter for the id */
    public void setId(final Integer id) {
        this.id = id;
    }

    /** getter for the lastname */
    public String getLastName() {
        return lastName;
    }

    /** setter for the lastname */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /** getter for the firstname */
    public String getFirstName() {
        return firstName;
    }

    /** setter for the firstname */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /** getter for the age */
    public Integer getAge() {
        return age;
    }

    /** setter for the age */
    public void setAge(final Integer age) {
        this.age = age;
    }

    /** getter for the city */
    public String getCity() {
        return city;
    }

    /** setter for the city */
    public void setCity(final String city) {
        this.city = city;
    }

    /** getter for the nice score */
    public Double getNiceScore() {
        return niceScore;
    }

    /** setter for the nice score */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    /** getter for the gift preferences */
    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /** setter for the gift preferences */
    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /** getter for the age category */
    public String getAgeCategory() {
        return ageCategory;
    }

    /** setter for the age category */
    public void setAgeCategory(final String ageCategory) {
        this.ageCategory = ageCategory;
    }

    /** method that allows a child to receive a gift */
    public void receiveGift(final List<Gift> sortedGifts) {
        Double money = this.assignedBudget;

        for (Category category : this.getGiftsPreferences()) {
            if (!this.getReceivedCategories().contains(category)) {
                for (Gift gift : sortedGifts) {
                    if (money >= gift.getPrice()) {
                        if (gift.getCategory() == category) {
                            if (!this.getReceivedGifts().contains(gift)) {
                                this.getReceivedGifts().add(gift);
                                this.getReceivedCategories().add(category);
                                money -= gift.getPrice();
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
