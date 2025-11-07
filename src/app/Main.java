package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //    public static void main(String[] args) throws FileNotFoundException {
    public static void main(String[] args) {
        /* Напишите программу, которая позволяет пользователю ввести список строк (например имена),
        и по завершение (например, если пользователь ввел "exit" сохраняет этот список в файл strings.txt */

        // Вариант 1. Из Main
        System.out.println("Вводите строки (для завершения введите 'exit'):");
        List<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            // вводим данные в список
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                strings.add(input);
            }
        }

        // Записываем список в файл
        try (FileWriter writer = new FileWriter("strings.txt")) {
            for (String s : strings) {
                writer.write(s + System.lineSeparator());
            }
            System.out.println("Строки успешно сохранены в файл strings.txt");
        } catch (IOException e) { // ловим исключения во время операций ввода-вывода
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }

        // Вариант 2. Из методов
        List<String> list = inputList();
        System.out.println(list);
        saveStrings(list);
    }

    public static List<String> inputList() {
        List<String> result = new ArrayList<>();

        System.out.println("Введите строки для завершения введите 'exit': ");
        String inputString = "";
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                inputString = scanner.nextLine();
                if (inputString.trim().equals("exit")) {
                    return result;
                } else {
                    result.add(inputString);
                }
            }
        }
    }

    public static void saveStrings(List<String> list) {
        try (Writer fileWriter = new FileWriter("strings.txt", true)) { // , true - разрешает дописывать в файл, а не перезаписывать
            for (var str : list) {
                fileWriter.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }
}