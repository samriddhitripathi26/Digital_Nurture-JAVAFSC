package Week1.TDD_JUnit_Mockito_SLF4J.MockingAndStubbing.src.main.java;

public class MyService {

    private ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public String fetchData() {
        return api.getData();
    }
}
