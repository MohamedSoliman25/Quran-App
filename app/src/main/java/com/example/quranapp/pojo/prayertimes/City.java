package com.example.quranapp.pojo.prayertimes;

//this is for json cities
public class City {

    private String country, name,arabicName;

    public City() {
    }



    public City(String country, String name, String arabicName) {
        this.country = country;
        this.name = name;
        this.arabicName = arabicName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    @Override
    public String toString() {
        return "City{" +
                "country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", arabicName='" + arabicName + '\'' +
                '}';
    }
}
