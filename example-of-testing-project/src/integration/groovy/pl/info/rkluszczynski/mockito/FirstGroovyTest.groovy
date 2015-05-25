package pl.info.rkluszczynski.mockito

class FirstGroovyTest extends GroovyTestCase {
    void setUp() {
        super.setUp()
        println 'gradleVariable: "' + System.getenv('gradleVariable') + '"'
    }

    void testSomething() {
        log.info('Testing something...')
        assertTrue(true)
    }
}
