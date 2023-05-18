package model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Creator {
    private  String name;
    private String country;

    public void setSouvenirs(List<Souvenir> souvenirs) {
        this.souvenirs = souvenirs;
    }

    private List<Souvenir> souvenirs = new ArrayList<>();

    public String getCountry() {
        return country;
    }

    public Creator(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public List<Souvenir> getSouvenirs(){
        return souvenirs;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Creator fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Creator.class);
    }

    @Override
    public String toString() {
        return "Creator{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", souvenirs=" + souvenirs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creator creator = (Creator) o;
        return Objects.equals(name, creator.name) && Objects.equals(country, creator.country) && Objects.equals(souvenirs, creator.souvenirs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, souvenirs);
    }
}
