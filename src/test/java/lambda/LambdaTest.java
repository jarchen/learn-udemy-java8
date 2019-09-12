package lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LambdaTest {
    @Test
    public void removeStringsWithMoreThanThreeCharacters() {
        List<String> input = asList("This", "is", "java", "8", "!!!");
        input = Lambda.filter(input, s -> s.isEmpty() || s.length() < 4);
        assertThat(input, hasItems("is", "8", "!!!"));
    }

    @Test
    public void shouldBeExecutedWithinATransaction() {
        TransactionLambda lambda = new TransactionLambda();
        Lambda.processWithinTransaction(lambda);
        assertTrue(lambda.isConsumed());
    }

    @Test
    public void shouldCreateAString() {
        String bigString = Lambda.create(() -> "Hello");
        assertTrue(bigString.length() > 0);
    }

    @Test
    public void extractStringSize() {
        String myString = "This is great";
        int length = Lambda.getStringLength(myString, String::length);
        assertEquals(13, length);
    }

    @Test
    public void multiply() {
        int a = 5;
        int b = 6;
        int result = Lambda.multiply(a, b, (intA, intB) -> intA * intB);
        assertEquals(30, result);
    }

    @Test
    public void shouldSortStrings() throws Exception {
        List<String> input = asList("C", "F", "A", "D", "B", "E");
        List<String> result = Lambda.sortStrings(input, Comparator.comparing(Function.identity()));
        assertThat(result, is(asList("A", "B", "C", "D", "E", "F")));
    }
}
