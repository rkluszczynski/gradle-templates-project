package pl.info.rkluszczynski.tutorial.java8.concurrency.dataset;

import java.util.ArrayList;
import java.util.List;

public class RandomTreeNode {
    private final String nodeName;
    private final List<RandomTreeNode> children = new ArrayList<>();

    public RandomTreeNode(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public List<RandomTreeNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
}
