package Week1.TDD_JUnit_Mockito_SLF4J.MockingAndStubbing.src.test.java;

import org.junit.jupiter.api.Test;

import Week1.TDD_JUnit_Mockito_SLF4J.MockingAndStubbing.src.main.java.ExternalApi;
import Week1.TDD_JUnit_Mockito_SLF4J.MockingAndStubbing.src.main.java.MyService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyServiceTest {

    @Test
    void testMocking() {

        ExternalApi api = mock(ExternalApi.class);

        when(api.getData()).thenReturn("Mock Data");

        MyService service = new MyService(api);

        assertEquals("Mock Data", service.fetchData());
    }
}
