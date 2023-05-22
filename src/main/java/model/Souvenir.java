package model;

import com.google.gson.Gson;

import java.util.Objects;

public class Souvenir {
    private String name;
    private String creatorName;
    private int year;
    private int price;
    private Creator creator;

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String creatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Souvenir(String name, String detailsOfManufacturer, int year, int price) {
        this.name = name;
        creatorName = detailsOfManufacturer;
        this.year = year;
        this.price = price;
    }

    public Creator getCreator() {
        return creator;
    }

    public Souvenir(String name, String detailsOfManufacturer, int year, int price, Creator creator) {
        this.name = name;
        creatorName = detailsOfManufacturer;
        this.year = year;
        this.price = price;
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

    public String getCreatorName() {
        return creatorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Souvenir souvenir = (Souvenir) o;
        return year == souvenir.year && price == souvenir.price && Objects.equals(name, souvenir.name) && Objects.equals(creatorName, souvenir.creatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creatorName, year, price, creator);
    }
}
