package pl.info.rkluszczynski.tutorial.java8.concurrency.dataset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RandomTree {
    private final RandomTreeNode root;
    private final List<RandomTreeNode> treeNodes = new ArrayList<>();

    public RandomTree(RandomTreeNode root) {
        this.root = root;
        treeNodes.add(root);
    }

    public RandomTreeNode getRoot() {
        return root;
    }

    public List<RandomTreeNode> getTreeNodes() {
        return treeNodes;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        LinkedList<NodeWithOffset> stack = new LinkedList<>();
        stack.add(new NodeWithOffset(root, TAB));

        while (!stack.isEmpty()) {
            NodeWithOffset node = stack.pollLast();
            buffer.append(node.getOffset() + node.getNode().getNodeName() + NL);

            List<RandomTreeNode> children = node.getNode().getChildren();
            if (!children.isEmpty()) {
                final String finalOffset = node.getOffset() + TAB;
                stack.addAll(children.stream()
                                .map(
                                        new Function<RandomTreeNode, NodeWithOffset>() {
                                            @Override
                                            public NodeWithOffset apply(RandomTreeNode treeNode) {
                                                return new NodeWithOffset(treeNode, finalOffset);
                                            }
                                        })
                                .collect(Collectors.toList())
                );
            }
        }
        return buffer.toString();
    }

    private class NodeWithOffset {
        private final RandomTreeNode node;
        private final String offset;

        public NodeWithOffset(RandomTreeNode node, String offset) {
            this.node = node;
            this.offset = offset;
        }

        public RandomTreeNode getNode() {
            return node;
        }

        public String getOffset() {
            return offset;
        }
    }

    private static final String NL = System.getProperty("line.separator");
    private static final String TAB = ".\t";
}
