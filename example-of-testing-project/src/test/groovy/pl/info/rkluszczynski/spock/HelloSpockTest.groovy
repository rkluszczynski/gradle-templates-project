import spock.lang.Specification

class HelloSpockTest extends Specification {

    def "word length test"() {
        expect:
        word.size() == length

        where:
        word    | length
        'Hello' | 5
        'Spock' | 5
    }
}
