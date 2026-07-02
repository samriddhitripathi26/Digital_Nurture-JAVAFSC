package Week1.DesignPatterns.FactoryMethodPatternExample;

public class WordFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}