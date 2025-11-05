package io.examples.complexcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AvoidComplexCode {

    // do not make a instance!!!!
    private AvoidComplexCode() {

    }

    public static Values theOldestWay(List<String> input) {
        List<List<String>> resListINList = new ArrayList<>();
        input.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Long result = 0L;
        for (int j = 0, inputSize = input.size(); j < inputSize; j++) {
            String element = input.get(j);
            List<String> splitted = Arrays.asList(element.split(" "));
            List<String> partResult = new ArrayList<>();

            for (int i = 0, splittedSize = splitted.size(); i < splittedSize; i++) {
                String part = splitted.get(i);
                if (part.trim().toUpperCase().contains("I")) {
                    result++;
                    partResult.add(part);
                }
            }
            resListINList.add(partResult);

        }
        return new Values(result, resListINList);
    }

    public static Values theOldWay(List<String> input) {

        List<List<String>> resListINList = new ArrayList<>();
        input.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Long result = 0L;
        for (String element : input) {
            List<String> splitted = Arrays.asList(element.split(" "));
            List<String> partResult = new ArrayList<>();

            for (String part : splitted) {
                if (part.trim().toUpperCase().contains("I")) {
                    result++;
                    partResult.add(part);
                }
            }
            resListINList.add(partResult);

        }
        return new Values(result, resListINList);
    }


    public static Values aBitStreamLined(List<String> input) {

        List<List<String>> resListINList = input.stream()
                .sorted()
                .map(e -> Arrays.stream(e.split(" "))
                        .filter(e1 -> e1.trim().toUpperCase().contains("I"))
                        .toList()).toList();
        AtomicReference<Long> counter = new AtomicReference<>(0L);
        resListINList.forEach(l1 ->
                l1.forEach(e ->
                        counter.getAndSet(counter.get() + 1))
        );
        return new Values(counter.get(), resListINList);
    }

    public static class Values {

        final List valList;

        public Values(Object... list) {
            valList = Arrays.asList(list);
        }

        public List getValList() {
            return Collections.unmodifiableList(valList);
        }
    }

    public static void printListList(List<List<String>> theList) {
        theList.forEach(l1 -> {
            System.out.println("Group-Change:");
            l1.forEach(e -> System.out.println(e));
        });
    }

    public static void printList(List<String> theList, String headLine) {
        System.out.println(headLine);
        theList.forEach(e ->
                System.out.println(e));
        System.out.println("--- PRINT out end ---");
    }

    public static void main(String[] arg) {
        List<String> inputOld = Arrays.asList("the evil hurricane",
                "a child plays",
                "in that case",
                "the walking dead",
                "I am alive",
                "Hello this is Romeo",
                "here to talk");
        List<String> inputStream = Arrays.asList("the evil hurricane",
                "a child plays",
                "in that case",
                "the walking dead",
                "I am alive",
                "Hello this is Romeo",
                "here to talk");
        printList(inputStream, "--- New Array before fun call ---");
        Values values = aBitStreamLined(inputStream);
        System.out.println("======== the new way with streams =============");
        printList(inputStream, "The new way has NO side effects at all - after fun call");
        long counter = (Long) values.getValList().get(0);
        List<List<String>> theList = (List<List<String>>) values.getValList().get(1);
        System.out.println("List counter: " + counter);
        printListList(theList);
        printList(inputOld, "--- Old Array before fun call ---");
        values = theOldWay(inputOld);
        System.out.println("======== the old way with loop =============");
        printList(inputOld, "The old way has side effects - after fun call");
        counter = (Long) values.getValList().get(0);
        theList = (List<List<String>>) values.getValList().get(1);
        System.out.println("List counter: " + counter);
        printListList(theList);
    }
}
