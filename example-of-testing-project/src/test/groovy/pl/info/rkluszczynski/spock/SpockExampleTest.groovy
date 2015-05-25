import spock.lang.Unroll

class SpockExampleTest extends spock.lang.Specification {

    def "test of abs value"() {
        expect:
        Math.abs(a) == b

        where:
        a << [-7, 7]
        b << [7, 7]
    }

    @Unroll("abs of #a is #b")
    def "test unroll of abs"() {
        expect:
        Math.abs(a) == b

        where:
        [a, b] << [[-3, 3], [3, 3]]
    }
}  