package pl.info.rkluszczynski.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Rafal
 */
public class MockitoExampleTest {

    @Mock
    private MockitoExample mockitoExampleUnderTest;
    
    @Before
    public void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void helloMockitoStringTest() {
	final String hiMockitoString = "Hi, Mockito!";	
	when(mockitoExampleUnderTest.helloMockito()).thenReturn(hiMockitoString);
	
	String returnedString = mockitoExampleUnderTest.helloMockito();

	Assert.assertTrue( hiMockitoString.equals(returnedString) );
    }    
    
}
