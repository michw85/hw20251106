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
        System.out.println("Вводите строки (для завершения введите 'exit'):");
        List<String> strings;
        strings = new ArrayList<>();
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


        /*
        Допустим дан текстовый файл, где имена записаны в одну строку записаны имена (разделены ;)
        jack;john;ann;nick
        ваша задача прочитать этот файл и получить список строк
         */

        StringBuilder result = new StringBuilder();
        try (Reader reader = new FileReader("names.txt")) {
            int data; // преобразование в байты - коды символов
            System.out.println("Данные из Файла:");
            // читаем посимвольно
            while ((data = reader.read()) != -1) { // байты закончились
                result.append((char) data); // получаем данные преобразовывая числа в символы
            }
            String names = result.toString().trim();  // получаем строку

            List<String> nameList = Arrays.asList(names.split(";")); // разбиваем по ;
            System.out.println("Список имён:");
            nameList.forEach(System.out::println);

        } catch (IOException e) { // ловим исключения во время операций ввода-вывода
            System.out.println("Ошибка чтения Файла");
        }


    }
}