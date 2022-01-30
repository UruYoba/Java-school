package HW7.core;


import java.util.Arrays;

public class Book {
    public String name;
    public String author;
    public String publishHouse;
    public boolean isInStock;
    public String series;
    String[] lastReaders;

    public Book(String name, String author, String publishHouse, boolean isInStock, String series, String[] lastReaders) {
        this.name = name;
        this.author = author;
        this.publishHouse = publishHouse;
        this.isInStock = isInStock;
        this.series = series;
        this.lastReaders = lastReaders;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publishHouse='" + publishHouse + '\'' +
                ", isInStock=" + isInStock +
                ", series='" + series;
    }
}
