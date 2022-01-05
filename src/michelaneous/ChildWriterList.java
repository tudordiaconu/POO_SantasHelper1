package michelaneous;

import java.util.ArrayList;

public class ChildWriterList {
    ArrayList<ChildWriter> children;

    public ChildWriterList() {
        this.children = new ArrayList<>();
    }

    public ArrayList<ChildWriter> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ChildWriter> children) {
        this.children = children;
    }
}
