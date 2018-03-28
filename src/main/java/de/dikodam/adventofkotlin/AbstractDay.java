package de.dikodam.adventofkotlin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class AbstractDay {

    public abstract void task1();

    public abstract void task2();

    public List<String> getInput(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(
            new File(getClass().getResource("/" + fileName).toURI())))) {
            return br.lines().collect(toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getInput() {
        String className = getClass().getSimpleName();
        System.out.println("fetching input for class " + className);
        return getInput(className);
    }

    protected static <T extends AbstractDay> void doTheMagic(Class<T> clazz) {
        try {
            T day = clazz.newInstance();
            day.task1();
            day.task2();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <D extends AbstractDay> void doTheMagicWithKotlin(D day) {
        day.task1();
        day.task2();
    }
}