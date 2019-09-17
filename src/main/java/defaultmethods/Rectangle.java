package defaultmethods;

public class Rectangle extends AbstractShape implements NamedObject {

    @Override
    public void notImplementedMethod() {
        System.out.println("Do something...");
    }
}