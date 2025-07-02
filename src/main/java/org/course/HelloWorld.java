package org.course;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}

public class HelloWorld {

    public static void main(String[] args) {
        Calculator adder = Integer::sum;
        System.out.println("10 + 5 = " + adder.operate(10, 5)); // Output: 10 + 5 = 15

        Calculator multiplier = (x, y) -> x * y;
        System.out.println("10 * 5 = " + multiplier.operate(10, 5)); // Output: 10 * 5 = 50

        Function<Integer, Integer> per10 = x -> x * 10;
        Function<Integer, Double> splitBy2 = x -> (double) (x / 2);

        Function<Integer, Double> per10SplitBy2 = per10
                .andThen(splitBy2)
                .andThen(x -> x * 3)
                .andThen(x -> x * 10);

        System.out.println(per10SplitBy2.apply(10)); // Output: 50
        System.out.println(per10SplitBy2.apply(25)); // Output: 125

        var pipeline = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                           .stream()
                           .map(e -> e * 5)
                           .filter(e -> e % 2 == 0)
                           .map(e -> e * 2)
                           .map(Object::toString);

        List<String> pippo = pipeline.collect(Collectors.toUnmodifiableList()); // [0, 20, 40, 60, 80]
        pippo.add("pluto");

    }
}