package HW13;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {

    private static int a;
    private static int b;
    private static Operation operation;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input = readConsole("Введите операцию");
        if (match(input)) {
            calculate();
        } else {
            System.out.println("Невалидный ввод данных.");
            throw new IllegalArgumentException("Невалидный ввод данных: " + input);
        }

    }

    /**
     * Производит подсчет по полученным данным
     */
    private static void calculate() {
        switch (operation) {
            case DIVIDE:
                try {
                    System.out.println(a / b);
                } catch (ArithmeticException e) {
                    System.out.println("Деление на ноль!");
                    throw new ArithmeticException("Деление на ноль!");
                }
                break;
            case PLUS:
                System.out.println(a + b);
                break;
            case MINUS:
                System.out.println(a - b);
                break;
            case MULTIPLY:
                System.out.println(a * b);
                break;
        }
    }

    /**
     * Проверяет, есть ли такая операция
     * @param op - операция после парсинга
     */
    public static void validateOperation(String op) {
        for (Operation values : Operation.values()) {
            boolean isValid = values.validateOperation(op);
            if (isValid) {
                operation = values;
                return;
            }
        }
        System.out.println("Некорректный ввод операции." + op);
        throw new IllegalStateException("Некорректный ввод операции. " + op);
    }

    private static boolean match(String input) {
        //вообще я хотел явно указать все допустимые операции. Но в задании нужно отрабатывать ошибки
        Matcher m = Pattern.compile(
                "(\\d+)\\s?([+\\-*/а-я]+)\\s?(\\d+).*"
        ).matcher(input);
        m.find();

        // валидируем операцию
        try {
            validateOperation(m.group(2));
        } catch (IllegalStateException e) {
            System.out.println("Отсутствует арифмитическая операция. " + input);
            throw new IllegalStateException("Отсутствует арифмитическая операция. " + input);
        }

        // парсим первый аргумент
        try {
            a = Integer.parseInt(m.group(1));
        } catch (NumberFormatException e) {
            System.out.println("Слишком большое число. " + input);
            throw new NumberFormatException("Некорректный ввод первого аргумента. " + input);
        } catch (IllegalStateException e) {
            System.out.println("Некорректный ввод первого аргумента. " + input);
            throw new IllegalStateException("Некорректный ввод первого аргумента. " + input);
        }
        //парсим второй
        try {
            b = Integer.parseInt(m.group(3));
        } catch (NumberFormatException e) {
            System.out.println("Слишком большое число." + input);
            throw new NumberFormatException("Некорректный ввод второго аргумента." + input);
        } catch (IllegalStateException e) {
            System.out.println("Некорректный ввод второго аргумента." + input);
            throw new IllegalStateException("Некорректный ввод второго аргумента." + input);
        }


        return m.matches();
    }

    public static String readConsole(String message) {
        System.out.print(message + ": ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("exit")) {
            System.exit(0);
        }
        return input;
    }
}
