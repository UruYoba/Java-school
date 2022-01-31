package HW8.core;

public enum Sector {
    A1("налево", "нижний"),
    A2("налево", "средний"),
    A3("налево", "верхний"),
    B1("направо", "нижний"),
    B2("направо", "средний"),
    B3("направо", "верхний"),
    VIP("в лифт", "VIP");

    public String path;
    public String floor;

    Sector(String path, String floor){
        this.path = path;
        this.floor = path;
    }

}
