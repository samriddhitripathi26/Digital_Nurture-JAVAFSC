package Week1.DesignPatterns.FactoryMethodPatternExample;

public class PdfFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}