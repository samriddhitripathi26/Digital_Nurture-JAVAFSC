package Week1.DesignPatterns.FactoryMethodPatternExample;

public class WordDocument implements Document {

    @Override
    public void open() {
        System.out.println("Opening Word Document");
    }
}