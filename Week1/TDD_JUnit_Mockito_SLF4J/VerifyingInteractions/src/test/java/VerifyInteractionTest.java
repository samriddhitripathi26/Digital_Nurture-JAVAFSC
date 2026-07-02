package Week1.TDD_JUnit_Mockito_SLF4J.VerifyingInteractions.src.test.java;

import org.junit.jupiter.api.Test;

import Week1.TDD_JUnit_Mockito_SLF4J.VerifyingInteractions.src.main.java.ExternalApi;
import Week1.TDD_JUnit_Mockito_SLF4J.VerifyingInteractions.src.main.java.MyService;

import static org.mockito.Mockito.*;

public class VerifyInteractionTest {

    @Test
    void testVerify() {

        ExternalApi api = mock(ExternalApi.class);

        MyService service = new MyService(api);

        service.fetchData();

        verify(api).getData();
    }
}
