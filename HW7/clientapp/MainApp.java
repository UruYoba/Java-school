package HW7.clientapp;

import HW7.core.Book;
import HW7.core.Library;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MainApp {

    private static Scanner scanner = new Scanner(System.in);
    private static Library lib = new Library();

    //каждое поле сделал приветным (кроме main), чтобы их нельзя было использовать извне
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Greetings! Library is ready for you.");
        startSearch();
    }

    /**
     * reads console and exit the app
     * @param question - text you want to print by reading console
     * @return - String answer
     */
    private static String readConsole(String question) {
        System.out.print(question.equals("") ? "" : question + ": ");
        String answer = scanner.nextLine().toLowerCase().trim();
        if (answer.equals("exit")) {
            System.exit(0);
        }
        return answer;
    }

    /**
     * reads category for filter books
     * @return - category for filter books
     */
    private static String readFilter() {
        System.out.println("Select category you want to search by:");
        int i = 1;
        for (String availableFilter : lib.filters) {
            System.out.println(
                    String.format(
                            "%d - %s",
                            i++,
                            availableFilter
                    ));
        }
        String filter;
        while (true) {
            int index;
            try {
                index = parseInt(readConsole("Enter number of category")) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input.");
                continue;
            }
            if (index >= 0 & index < lib.filters.length - 1) {
                filter = lib.filters[index];
                return filter;
            } else {
                System.out.println("Incorrect input");
            }
        }
    }

    /**
     * start search by query and filter
     */
    private static void startSearch() {
        String filter = "";
        String query;
        boolean isFilterSelected = false;
        while (true) {
            if (!isFilterSelected) {
                filter = readFilter();
                isFilterSelected = true;
            } else {
                query = readConsole("Enter your query");
                if (query.equals("back")) {
                    isFilterSelected = false;
                    continue;
                }
                lib.searchBook(filter, query);
            }
        }
    }
}
