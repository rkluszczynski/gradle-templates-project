package pl.info.rkluszczynski.tutorial.java8.concurrency.dataset;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomTreeGenerator {
    private final Random random = new Random();
    private final int maxChildrenCount;
    private final int atLeastNodesCountLimit;

    private int nodesCount = 1;

    public RandomTreeGenerator(int maxChildrenCount, int atLeastNodesCountLimit) {
        this.maxChildrenCount = maxChildrenCount;
        this.atLeastNodesCountLimit = atLeastNodesCountLimit;
    }

    public RandomTree generate() {
        LinkedList<RandomTreeNode> queue = new LinkedList<>();
        RandomTreeNode root = new RandomTreeNode("root");
        RandomTree tree = new RandomTree(root);
        List<RandomTreeNode> nodes = randomizeChildrenList(root, tree);

        queue.addAll(nodes);
        while (!queue.isEmpty()) {
            RandomTreeNode node = queue.pollFirst();
            List<RandomTreeNode> childNodes = randomizeChildrenList(node, tree);
            queue.addAll(childNodes);
        }
        return tree;
    }

    private List<RandomTreeNode> randomizeChildrenList(RandomTreeNode node, RandomTree tree) {
        int childrenCount = (nodesCount > atLeastNodesCountLimit) ? 0 : random.nextInt(maxChildrenCount) + 1;

        List<RandomTreeNode> childNodes = generateChildrenList(childrenCount);
        node.getChildren().addAll(childNodes);
        tree.getTreeNodes().addAll(childNodes);
        return childNodes;
    }

    private List<RandomTreeNode> generateChildrenList(int count) {
        nodesCount += (count);
        return IntStream.range(nodesCount - count, nodesCount)
                .mapToObj(value -> new RandomTreeNode(String.format("node-%03d", value)))
                .collect(Collectors.toList());
    }
}
