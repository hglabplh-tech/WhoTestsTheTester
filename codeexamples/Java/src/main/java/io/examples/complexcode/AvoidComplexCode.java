package io.examples.complexcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class AvoidComplexCode {

    // do not make a instance!!!!
    private AvoidComplexCode (){

    }

    public static void tooMuchNestedForCollect() {
        AtomicReference<List<Integer>> nonsense;
        nonsense = new AtomicReference<>(new ArrayList<>());
        Consumer<AtomicReference<List<Integer>>> atomicReferenceConsumer = AvoidComplexCode::accept;
        atomicReferenceConsumer.accept(nonsense);
    }

    public static void unrolledHmpf() {
        AtomicReference<List<Integer>> nonsense = new AtomicReference<>(new ArrayList<>());
        accept(nonsense);
    }

    private static void accept(AtomicReference<List<Integer>> nonsense1) {
        {
            List<String> strList = Arrays.asList("Hello this is Romeo".split(" "));
            Integer counter = Integer.valueOf(0);
            for (String part : strList)
                if (part.trim().equals("I")) {
                    nonsense1.get().add(counter++);
                }
        }
        {
            List<String> strList = Arrays.asList("here to talk".split(" "));
            Integer counter = Integer.valueOf(0);
            for (String part : strList) {
                if (part.trim().equals("I")) {
                    nonsense1.get().add(counter++);
                }
            }
        }
        {
            List<String> strList = Arrays.asList("I am alive".split(" "));
            Integer counter = Integer.valueOf(0);
            for (String part : strList) {
                if (part.trim().equals("I")) {
                    nonsense1.get().add(counter++);
                }
            }
        }
        {
            List<String> strList = Arrays.asList("am I crazy".split(" "));
            Integer counter = Integer.valueOf(0);
            for (String part : strList) {
                if (part.trim().equals("I")) {
                    nonsense1.get().add(counter++);
                }
            }
        }
        {
            List<String> strList = Arrays.asList("the walking dead".split(" "));
            Integer counter = Integer.valueOf(0);
            for (String part : strList) {
                if (part.trim().equals("I")) {
                    nonsense1.get().add(counter++);
                }
            }
        }
        {
            List<String> strList = Arrays.asList("in that case".split(" "));
            Integer counter = Integer.valueOf(0);
            for (String part : strList) {
                if (part.trim().equals("I")) {
                    nonsense1.get().add(counter++);
                }
            }
        }
        {
            List<String> strList = Arrays.asList("a child plays".split(" "));
            Integer counter = Integer.valueOf(0);
            for (String part : strList) {
                if (part.trim().equals("I")) {
                    nonsense1.get().add(counter++);
                }
            }
        }
        {
            List<String> strList = Arrays.asList("the evil hurricane".split(" "));
            Integer counter = Integer.valueOf(0);
            for (int i = 0, strListSize = strList.size(); i < strListSize; i++) {
                String part = strList.get(i);
                if (part.trim().equals("I")) {
                    nonsense1.get().add(counter++);
                }
            }
        }
    }

    public void abitStreamLined() {
        List <String> input = Arrays.asList("the evil hurricane",
                "a child plays",
                "in that case",
                "the walking dead",
                "I am alive",
                "Hello this is Romeo",
                "here to talk" );
        long result =  input.stream()
                .sorted()
                .map(e -> Arrays.stream(e.split(" "))
                        .filter(e1 -> e1.trim().equals("I"))
                        .findAny().orElse("null")).count();
    }
}
