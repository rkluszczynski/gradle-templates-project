package pl.info.rkluszczynski.tutorial.java8.concurrency.ex1parray

import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.ForkJoinPool

import static pl.info.rkluszczynski.tutorial.java8.concurrency.StopWatchUtil.timing

class Exercise1ProcessorTest extends Specification {
    Exercise1Processor exercise1Processor = new Exercise1Processor()
    @Shared
    int[] data

    def setupSpec() {
//        FIXME:
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");

        data = DataArrayGenerator.sequential(10_000_000)
//        FIXME:
//        data = DataArrayGenerator.parallel()

        System.out.println(ForkJoinPool.commonPool().getParallelism());
    }

    def 'should parallel sort be quicker than sequential'() {
        given:
        def expectedData
        def seqTiming = timing {
            expectedData = exercise1Processor.seqSort(data)
        }
        println seqTiming

        when:
        def resultData
        def parTiming = timing {
            resultData = exercise1Processor.parSort(data)
        }
        println parTiming

        then:
        resultData == expectedData
        parTiming < 0.8 * seqTiming
    }

    def 'should parallel prefix be quicker than sequential'() {
        given:
        def expectedData
        def seqTiming = timing {
            expectedData = exercise1Processor.seqPrefix(data)
        }
        println seqTiming

        when:
        def resultData
        def parTiming = timing {
            resultData = exercise1Processor.parPrefix(data)
        }
        println parTiming

        then:
        resultData == expectedData
        parTiming < 0.8 * seqTiming
    }
}
