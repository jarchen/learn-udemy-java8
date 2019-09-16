package stream;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Stream8 {

    public static List<Integer> returnSquareRoot(List<Integer> numbers) {
        return numbers.stream().map(Math::sqrt).map(Double::intValue).collect(toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> users) {
        return users.stream().map(User::getAge).collect(toList());
    }

    public static List<Integer> getDistinctAges(List<User> users) {
        return users.stream().map(User::getAge).distinct().collect(toList());
    }

    public static List<User> getLimitedUserList(List<User> users, int limit) {
        return users.stream().limit(limit).collect(toList());
    }

    public static Integer countUsersOlderThen25(List<User> users) {
        long count = users.stream().map(User::getAge).filter(age -> age > 25).count();
        return new Long(count).intValue();
    }

    public static List<String> mapToUpperCase(List<String> strings) {
        return strings.stream().map(String::toUpperCase).collect(toList());
    }

    public static Integer sum(List<Integer> integers) {
//        return integers.stream().mapToInt(value -> value).sum();
        return integers.stream().reduce(0, Integer::sum);
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip) {
        return integers.stream().skip(toSkip).collect(toList());
    }

    public static List<String> getFirstNames(List<String> names) {
        return names.stream().map(n -> n.split(" ")[0]).collect(toList());
    }

    public static List<String> getDistinctLetters(List<String> names) {
        return names.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(toList());
    }


    public static String separateNamesByComma(List<User> users) {
        return users.stream().map(User::getName).collect(joining(", "));
    }

    public static double getAverageAge(List<User> users) {
        return users.stream().mapToInt(User::getAge).average().orElse(-1);
    }

    public static Integer getMaxAge(List<User> users) {
        return users.stream().mapToInt(User::getAge).max().orElse(-1);
    }

    public static Integer getMinAge(List<User> users) {
        return users.stream().mapToInt(User::getAge).min().orElse(-1);
    }

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users) {
        return users.stream().collect(partitioningBy(User::isMale));
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users) {
        return users.stream().collect(groupingBy(User::getAge));
    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users) {
        return users.stream().collect(groupingBy(User::isMale, groupingBy(User::getAge)));
    }

    public static Map<Boolean, Long> countGender(List<User> users) {
        return users.stream().collect(groupingBy(User::isMale, counting()));
    }

    public static boolean anyMatch(List<User> users, int age) {
        return users.stream().anyMatch(user -> user.getAge().equals(age));
    }

    public static boolean noneMatch(List<User> users, int age) {
        return users.stream().noneMatch(user -> user.getAge().equals(age));
    }

    public static Optional<User> findAny(List<User> users, String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findAny();
    }

    public static List<User> sortByAge(List<User> users) {
        users.sort(Comparator.comparing(User::getAge));
        return users;
    }

    public static Stream<Integer> getBoxedStream(IntStream stream) {
        return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers() {
        return Stream.iterate(2, integer -> integer + 1).filter(Stream8::isPrime).limit(10).collect(toList());
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number / 2).noneMatch(i -> number % i == 0);
    }

    public static List<Integer> generate10RandomNumbers() {
        return Stream.generate(() -> {
            Random r = new Random();
            return r.nextInt();
        }).limit(10).collect(toList());
    }

    public static User findOldest(List<User> users) {
        return users.stream().max(Comparator.comparing(User::getAge)).get();
    }

    public static int sumAge(List<User> users) {
        return users.stream().mapToInt(User::getAge).sum();
    }

    public static IntSummaryStatistics ageSummaryStatistics(List<User> users) {
        return users.stream().mapToInt(User::getAge).summaryStatistics();
    }

}