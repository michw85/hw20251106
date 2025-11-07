package app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        /*
        Допустим дан текстовый файл, где имена записаны в одну строку записаны имена (разделены ;)
        jack;john;ann;nick
        ваша задача прочитать этот файл и получить список строк
         */

        // Вариант 1. Из Main
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

        // Вариант 2. Из методов
        List<String> list = readFile();
        System.out.println(list);
    }

    public static List<String> readFile() {
        String fileName = "names.txt";
        List<String> result = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName)) {
            int data;
            StringBuilder sb = new StringBuilder();
            while ((data = reader.read()) != -1) {
                char ch = (char) data;
                if (ch == ';') {
                    result.add(sb.toString());
                    sb.setLength(0); // обрезали длину StringBuilder
                } else {
                    sb.append(ch);
                }
            }
            if (sb.length()>0){ // если что-то осталось - дочитываем и записываем из файла
                result.add(sb.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("не найден файл" + fileName);
        } catch (IOException e) {
            System.out.println("ошибка чтения файла");
        }
        return result;
    }

    public static List<String> readFile2() {
        String fileName = "names.txt";
        List<String> result = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName)) {
            int data;
            StringBuilder sb = new StringBuilder();
            while ((data = reader.read()) != -1) {
                char ch = (char) data;
                sb.append(ch);
            }
            String resString = sb.toString();
            return Arrays.asList(resString.split(";"));
        } catch (FileNotFoundException e) {
            System.out.println("не найден файл" + fileName);
        } catch (IOException e) {
            System.out.println("ошибка чтения файла");
        }
        return result;
    }
}
