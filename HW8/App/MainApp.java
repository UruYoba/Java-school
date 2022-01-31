package HW8.App;

import HW8.core.Sector;
import HW8.core.Ticket;
import HW8.core.Turnstile;

import java.util.Arrays;
import java.util.Random;

public class MainApp {
    private static final Random rnd = new Random();
    private static Turnstile turnstile;

    public static void main(String[] args) {
        Ticket[] tickets = generateTickets(10);
        turnstile = new Turnstile(tickets);
        Ticket[] validatedTickets = turnstile.startWork();
        System.out.println(Arrays.toString(validatedTickets));

    }

    public static Ticket[] generateTickets(int ticketsCount) {
        Ticket[] tickets = new Ticket[ticketsCount];
        for (int i = 0; i < ticketsCount; i++) {
            String sector = rnd.nextBoolean() ? getRandomSector() : getRandomString(10);
            tickets[i] = new Ticket(
                    getRandomString(10),
                    String.format("%d", rnd.nextInt(200) + 100),
                    // изначально хотел больший номер билета, но понял, что тогда вряд ли проверю уникальность билета
                    rnd.nextInt(150),
                    sector
            );
        }
        return tickets;
    }

    private static String getRandomSector() {
        Sector[] values = Sector.values();
        String tick = values[rnd.nextInt(values.length)].name();
        return tick;
    }

    private static String getRandomString(int maxLength) {
        String str = "йцукенгшщзхъфывапролджэячсмитьбюёЪЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮЁ0123456789";
        StringBuffer sb = new StringBuffer();
        int length = rnd.nextInt(maxLength);
        for (int i = 0; i < length; i++) {
            int number = rnd.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
