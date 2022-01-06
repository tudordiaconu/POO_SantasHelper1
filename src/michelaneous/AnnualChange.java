package michelaneous;

import java.util.ArrayList;

public class AnnualChange {
    private Double newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<Child> newChildren;
    private ArrayList<ChildUpdate> childrenUpdates;

    public AnnualChange() {
        this.newSantaBudget = null;
        this.newGifts = null;
        this.newChildren = null;
        this.childrenUpdates = null;
    }


    /** getter for the new budget */
    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    /** setter for the new budget */
    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    /** getter for the new gifts */
    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    /** setter for the new gifts */
    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    /** getter for the new children */
    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    /** setter for the new children */
    public void setNewChildren(final ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    /** getter for the children updates */
    public ArrayList<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    /** setter for the children updates */
    public void setChildUpdates(final ArrayList<ChildUpdate> childrenUpdate) {
        this.childrenUpdates = childrenUpdate;
    }
}
