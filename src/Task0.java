import java.io.*;
import java.util.*;

/**
 * Задание 0
 * Вернемся к домашнему заданию занятия номер 11 и модифицируем его.
 *
 * Программа должна получать имена файлов с номерами документов с консоли: каждая новая строка - это путь к файлу и имя файла.
 * Для завершения ввода списка файлов следует ввести 0.
 * После получения списка документов программа должна обработать каждый документ: вычитать из файла номера документов и провалидировать их.
 * В конце работы создать один файл отчет с выходной информаций: номер документа - комментарий(валиден или не валиден и по какой причине).
 * Пусть каждый файл содержит каждый номер документа с новой строки и в строке никакой другой информации, только номер документа.
 * Валидный номер документа должен иметь длину 15 символов и начинаться с последовательности docnum(далее любая последовательность букв/цифр) или kontract(далее любая последовательность букв/цифр).
 * Учесть, что номера документов могут повторяться в пределах одного файла и так же разные документы могут содержать одни и те же номера документов.
 * Если номера документов повторяются, то повторные номера документов не проверять, не валидировать.
 */

public class Task0 {
    public static void main(String[] args) {
        System.out.println("Введите пути к файлам.");
        Scanner scan = new Scanner(System.in);

        ArrayList<String> pathList = new ArrayList<>();
        Set<String> docnumListSet = new HashSet<>();
        Map<String, String> docNumListMap = new HashMap();

        String exitSymbol = "0";

        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {

            File myFile = new File("DocNumList.txt");

            while (scan.hasNext()) {
                String filePath = scan.nextLine();
                if (!filePath.equals(exitSymbol)) {
                    pathList.add(filePath);
                }
                if (filePath.equals(exitSymbol)) {
                    break;
                }
            }

            for (String s : pathList) {
                System.out.println(s);
                File fileS = new File(s);
                fileReader = new FileReader(s);
                Scanner scan1 = new Scanner(fileReader);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    docnumListSet.add(str);
                }
            }
            System.out.println(docnumListSet);

            for (String docNum : docnumListSet) {
                if (docNum.length() == 15 && docNum.startsWith("docnum")) {
                    docNumListMap.put(docNum, "Valid");
                } else {
                    docNumListMap.put(docNum, "invalid");
                }
            }
            for (Map.Entry<String, String> item : docNumListMap.entrySet()) {
                System.out.println(item.getKey() + " " + item.getValue());
                fileWriter = new FileWriter(myFile, true);
                fileWriter.write(item.getKey() + " " + item.getValue() + "\n");
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ioException) {
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        }
    }
}

