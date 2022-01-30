package HW7.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {
    int bookCount;
    Book[] books;
    String[] readerList;
    public String[] filters;

    public Library() {
        this.bookCount = 0;
        // Я знаю, плохая практика кода. Но мне было очень лень снова организовывать считывание файла, так гораздо быстрее
        this.readerList = "Кириллова Асия Адамовна;Лебедева Виктория Александровна;Калинин Андрей Дмитриевич;Гаврилов Тимофей Русланович;Баранов Савелий Тимурович;Рябинин Данил Дмитриевич;Самойлов Дмитрий Степанович;Попов Матвей Максимович;Лебедева Марьяна Вадимовна;Козлова Мария Кирилловна;Зотова Дарья Данииловна;Субботин Михаил Львович;Кузнецов Лев Егорович;Семенова Малика Владимировна;Воронина Виктория Андреевна;Воронова Варвара Максимовна;Новикова Диана Викторовна;Некрасов Максим Степанович;Чернышева Майя Матвеевна;Васильев Василий Андреевич;Розанова Николь Дмитриевна;Хохлов Роман Иванович;Сафонов Владимир Александрович;Казаков Владимир Сергеевич;Климова Арина Марковна;Воронина Агата Ильинична;Мещерякова Мария Ильинична;Севастьянов Даниил Богданович;Козлова Евгения Руслановна;Демидов Вячеслав Олегович;Никулин Мирослав Владимирович;Марков Сергей Викторович;Филатова Мария Максимовна;Еремеева Ева Денисовна;Виноградова Анастасия Константиновна;Крылов Савва Андреевич;Кондрашова Полина Григорьевна;Платонова Мия Тимуровна;Королев Даниил Ильич;Максимов Егор Давидович;Ковалев Александр Никитич;Жуков Иван Семёнович;Крюкова Ангелина Ярославовна;Русаков Даниил Егорович;Ткачев Давид Алексеевич;Романов Кирилл Дмитриевич;Леонов Александр Тимурович;Зимин Гордей Ярославович;Горячева Айлин Романовна;Борисов Данил Даниилович;Малышев Алексей Маркович;Щербаков Михаил Максимович;Игнатьева Василиса Давидовна;Беляков Мирон Александрович;Моисеев Богдан Владимирович;Рыжов Марк Алексеевич;Волков Кирилл Тимурович;Никитин Артём Егорович;Зверев Ярослав Михайлович;Дмитриев Кирилл Андреевич;Антонов Максим Маркович;Власова Есения Тимуровна;Уткин Тимофей Семёнович;Завьялов Кирилл Алексеевич;Голованова Стефания Глебовна;Андреев Артём Никитич;Александров Михаил Павлович;Сухарева Милана Демидовна;Воробьева Алёна Константиновна;Дмитриева Майя Максимовна;Карпова Виктория Никитична;Зеленина Мадина Савельевна;Волошина Татьяна Степановна;Сахарова Анна Евгеньевна;Баранова Анна Дмитриевна;Кузнецова Вера Леонидовна;Головин Дмитрий Олегович;Никольский Борис Иванович;Рыбаков Илья Игоревич;Фадеев Эмир Георгиевич;Кудрявцев Елисей Егорович;Давыдова Яна Сергеевна;Смирнова Валерия Никитична;Петрова Анна Матвеевна;Смирнова Екатерина Максимовна;Москвин Константин Константинович;Николаева Мария Леонидовна;Федоров Михаил Семёнович;Устинов Ярослав Андреевич;Смирнов Иван Дмитриевич;Горшкова Ольга Максимовна;Смирнова Валерия Захаровна;Кудрявцева Ксения Михайловна;Калачева Вероника Юрьевна;Яковлев Лука Маркович;Фирсов Михаил Константинович;Ильинская Валерия Александровна;Суслов Данил Дмитриевич;Воронов Александр Иванович;Баранов Эмиль Романович".split(";");
        readLibraryData("fiction");
        this.filters = new String[]{"name", "author", "series", "publishHouse"};
    }

    public void searchBook(String filter, String query) {
        String value;
        int bookCounter = 0;
        for (Book book : books) {
            try {
                value = (String) book.getClass().getField(filter).get(book);
            } catch (NoSuchFieldException e) {
                System.out.println("Incorrect query input");
                return;
            } catch (IllegalAccessException e) {
                System.out.println("Incorrect category input");
                return;
            }
            if (value.toLowerCase().trim().contains(query)){
                bookCounter++;
                System.out.println(book);
            }
        }
        if (bookCounter == 0){
            System.out.println("None of the books match query: " + query);
        }
    }

    public Book[] getBooks() {
        return this.books;
    }

    /**
     * search file with book data. If it exists, generates Library.
     *
     * @param fileName - name of the file with book data.
     */
    private void readLibraryData(String fileName) {
        String filePath = String.format(
                "HW7/core/Books/%s.txt",
                fileName
        );
        File fiction = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(fiction);
        } catch (FileNotFoundException e) {
            System.out.println(filePath + " has not been found.");
            System.exit(-1);
        }
        readFile(scanner);
    }

    private void readFile(Scanner scanner) {
        String fileData = "";
        while (scanner.hasNextLine()) {
            bookCount++;
            fileData += scanner.nextLine() + "\n";
        }
        books = new Book[bookCount];
        // регулярка немного неправильная. Я не знаю, как включить в поиск спецсимволы. Но большую часть книг обрабатывает
        // данные о книгах с сайта Labirint. Не зря писал парсер)
        Pattern p = Pattern.compile("([\\d\\.\\wА-Яа-я\\s]+) \\|\\| ([.\\.\\wА-Яа-я\\s]+) \\|\\| ([.\\wА-Яа-я\\s]+)[\\.:]?([А-Яа-я\\s,\\.]+)");
        int iter = 0;
        Random rnd = new Random();
        for (String line : fileData.split("\\n")) {
            Matcher m = p.matcher(line);
            if (m.find()) {
                String bookName = m.group(1);
                String author = m.group(2);
                String pubHouse = m.group(3);
                String series = m.group(4);
                Book book = new Book(
                        bookName,
                        author,
                        pubHouse,
                        rnd.nextBoolean(),
                        series,
                        getReaderList()
                );
                if (book == null) { //из-за не до конца правильной регулярки некоторые значения нулевые
                    continue;
                }
                books[iter++] = book;
            }
        }
        books = Arrays.copyOf(books, iter);
    }

    /**
     * Set random readers to book from reader list of Library.
     *
     * @return list of last ten readers of the book. There can be empty (null).
     */
    private String[] getReaderList() {
        Random rnd = new Random();
        String[] readerList = new String[10];
        int readersCount = rnd.nextInt(11);
        for (int i = 0; i < readersCount; i++) {
            readerList[i] = this.readerList[rnd.nextInt(100)];
        }
        return readerList;
    }
}
