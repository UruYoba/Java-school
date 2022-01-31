package HW11.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {
    String logs;
    private String regExp;
    private Pattern p;

    public Logger(String log) {
        this.logs = log;
        regExp = "(\\d{4}-\\d{2}-\\d{2}) ([\\d:\\.]{12}).*-{3} \\[\\s[a-zA-z]+\\](\\s[a-zA-z\\.]+[\\[\\]\\/]*) : (.*)";
        p = Pattern.compile(regExp);
    }

    /**
     * Получение данных из строки
     * @param log - строка
     * @return - массив с данными
     */
    private String[] match(String log) {
        String[] data = new String[4];
        Matcher m = p.matcher(log);
        if (m.find()) {
            data[0] = m.group(1).trim(); //дата
            data[1] = m.group(2).trim(); // время
            data[2] = m.group(3).trim(); //инициатор
            data[3] = m.group(4).trim(); // сообщение
        }
        return data;
    }

    /**
     * Вывод времени и сообщения валидных строк
     */
    public void showTimeAndMessage() {
        Pattern p = Pattern.compile(regExp);
        int iter = 0;
        for (String log : logs.split("\n")) {
            String[] data = match(log);
            if(!validateLog(data)){
                continue; // пропускаем невалидные строки
            }
            iter++;
            System.out.println(
                    String.format(
                            "Время - %s, сообщение %s",
                            data[1],
                            data[3]
                    )
            );
        }
    }

    /**
     * валидация данных
     * @param data - массив с данными
     * @return - boolean валидные ли данные
     */
    public boolean validateLog(String[] data){
        return !Arrays.asList(data).contains(""); //проверяем, что нет null
    }

    /**
     * Вывод на экран валидных строк без времени
     */
    public void setUnknownTime(){
        for(String log: logs.split("\n")){
            String[] data = match(log);
            if(!validateLog(data)){
                continue; // пропускаем невалидные строки
            }
            System.out.println(
                    log.replaceFirst("[\\d:\\.]{12}", "<time unknown>")
            );
        }
    }
}
