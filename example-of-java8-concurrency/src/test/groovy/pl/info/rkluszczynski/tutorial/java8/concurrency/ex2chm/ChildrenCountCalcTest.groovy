package pl.info.rkluszczynski.tutorial.java8.concurrency.ex2chm

import pl.info.rkluszczynski.tutorial.java8.concurrency.dataset.RandomTree
import pl.info.rkluszczynski.tutorial.java8.concurrency.dataset.RandomTreeGenerator
import spock.lang.Shared
import spock.lang.Specification

import java.util.stream.Collectors

import static pl.info.rkluszczynski.tutorial.java8.concurrency.StopWatchUtil.timing

class ChildrenCountCalcTest extends Specification {
    ChildrenCountCalc calcUnderTest = new ChildrenCountCalc()
    @Shared
    RandomTree tree

    def setupSpec() {
        tree = new RandomTreeGenerator(11, 519).generate()
    }

    def 'should calculate children counts for every node faster than counting naively'() {
        given:
        def expectedCounts
        def slowTiming = timing {
            expectedCounts = calculateCountsMap { node -> calcUnderTest.countNodesNaively(node) }
        }
        println slowTiming

        when:
        def nodesCounts
        def betterTiming = timing {
            nodesCounts = calculateCountsMap { node -> calcUnderTest.countNodes(node) }
        }
        println betterTiming

        then:
        nodesCounts == expectedCounts
        betterTiming < 0.8 * slowTiming
    }

    private def calculateCountsMap(Closure callable) {
        tree.treeNodes.stream()
                .collect(Collectors.toMap({ node -> node.getNodeName() }, callable));
    }
}
