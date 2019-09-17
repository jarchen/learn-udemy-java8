package defaultmethods;

public interface NamedObject {

    default String getName() {
        return "NamedObject";
    }
}