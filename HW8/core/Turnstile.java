package HW8.core;

import java.util.Arrays;

public class Turnstile {

    Ticket[] tickets;
    private String[] usedTicketsNumbers;

    public Turnstile(Ticket[] tickets) {
        this.tickets = tickets;
        usedTicketsNumbers = new String[tickets.length];
    }

    /**
     * Validates tickets and greets if ticket is valid
     * @return - array of validated tickets
     */
    public Ticket[] startWork() {
        String[] numbers = new String[tickets.length];
        Ticket[] validatedTickets = new Ticket[tickets.length];
        int iter = 0;
        for (Ticket ticket : tickets) {
            if (validateTicket(ticket)) {
                if (Arrays.asList(numbers).contains(ticket.number)) {
                    System.out.println("Билет уже был использован.");
                    continue;
                }
                validatedTickets[iter] = ticket;
                numbers[iter++] = ticket.number;
                greetPerson(ticket);
            }
        }
        usedTicketsNumbers = Arrays.copyOf(usedTicketsNumbers,  iter);
        return Arrays.copyOf(validatedTickets, iter);
    }

    /**
     * greets person
     * @param ticket - Ticket[] ticket
     */
    private static void greetPerson(Ticket ticket){
        String path = "Пройдите пожалуйста %s в %s сектор и займите %s место.";
        switch (ticket.sector) {
            case "A1":
            case "A2":
                path += " Сохраняйте билеты до конца матча.";
                break;
            case "VIP":
                System.out.println("Мы рады видеть Вас " + ticket.name + "! Приятной игры.");
                break;
        }
        System.out.println(
                String.format(
                        path,
                        Sector.valueOf(ticket.sector).path,
                        ticket.sector,
                        ticket.sector.equals("VIP") ? "любое" : String.format("%d", ticket.seat)
                )
        );
    }

    /**
     * Validates single ticket
     * @param ticket - Ticket ticket
     * @return - true if ticket is valid
     */
    public static boolean validateTicket(Ticket ticket) {
        String problems = "";
        if (ticket.name.length() == 0) {
            problems += "Невалидное имя.|";
        }
        try {
            Arrays.asList(Sector.values()).contains(Sector.valueOf(ticket.sector));
        } catch (IllegalArgumentException e) {
            problems += "Невалидный сектор стадиона: " + ticket.sector + "|";
        }
        if (ticket.seat <= 0 | ticket.seat > 100) {
            problems += "Такого места нет " + ticket.seat + "|";
        }
        if (!ticket.number.trim().matches("\\d+")) {
            problems += "Невалидный номер билета " + ticket.seat + "|";
        }
        boolean isTicketOkay = problems.length() == 0;
        if (!isTicketOkay) {
            System.out.println("\n######");
            System.out.println("Ticket №" + ticket.number);
            System.out.println(String.join("\n", problems.split("\\|")));
            System.out.println("######\n");
        }
        return isTicketOkay;
    }
}
