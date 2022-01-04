package michelaneous;

import enums.Category;

import java.util.ArrayList;

public class ChildUpdate {
    private Integer id;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;

    public ChildUpdate() {
        this.id = null;
        this.niceScore = null;
        this.giftsPreferences = null;
    }

    public ChildUpdate(Integer id, Double niceScore, ArrayList<Category> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(Double niceScore) {
        this.niceScore = niceScore;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
