package pl.info.rkluszczynski.tutorial.java8.concurrency.ex2chm;

import pl.info.rkluszczynski.tutorial.java8.concurrency.dataset.RandomTreeNode;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChildrenCountCalc {
    private Map<String, Integer> cache = new ConcurrentHashMap<>();

    public int countNodes(RandomTreeNode node) {
//        TODO

        List<RandomTreeNode> children = node.getChildren();
        if (children.isEmpty()) return 1;

//        Integer i = cache.get(node.getNodeName());
//        if (i != null) {
//            return i.intValue();
//        }
//        int count = 1 + children.stream().mapToInt(child -> countNodes(child)).sum();
//        cache.put(node.getNodeName(), count);
//        return  count;
        return cache.computeIfAbsent(node.getNodeName(),
                (key) -> children.stream()
                        .mapToInt(child -> countNodes(child))
                        .sum() + 1);
    }

    public int countNodesNaively(RandomTreeNode node) {
        List<RandomTreeNode> children = node.getChildren();
        if (children.isEmpty()) return 1;

        return 1 + children.stream()
                .mapToInt(child -> countNodesNaively(child))
                .sum();
    }
}