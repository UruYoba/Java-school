package HW8.core;

public class Ticket {
    public String name;
    public String number;
    public int seat;
    public String sector;

    public Ticket(String name, String number, int seat, String sector) {
        this.name = name; // не пустая строка
        this.number = number; // не должен повторяться
        this.seat = seat; // не больше 100
        this.sector = sector; // должен существовать
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", seat=" + seat +
                ", sector='" + sector + '\'' +
                '}';
    }
}
