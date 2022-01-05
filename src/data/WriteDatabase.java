package data;

import michelaneous.ChildWriterList;

import java.util.ArrayList;

public class WriteDatabase {
    private ArrayList<ChildWriterList> annualChildren;

    public WriteDatabase() {
        this.annualChildren = new ArrayList<>();
    }

    public ArrayList<ChildWriterList> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(ArrayList<ChildWriterList> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
