package Week1.DesignPatterns.FactoryMethodPatternExample;

public class ExcelFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}