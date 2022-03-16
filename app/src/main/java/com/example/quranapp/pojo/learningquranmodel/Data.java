package com.example.quranapp.pojo.learningquranmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("englishName")
    @Expose
    private String englishName;
    @SerializedName("englishNameTranslation")
    @Expose
    private String englishNameTranslation;
    @SerializedName("revelationType")
    @Expose
    private String revelationType;
    @SerializedName("numberOfAyahs")
    @Expose
    private Integer numberOfAyahs;
    @SerializedName("ayahs")
    @Expose
    private List<Ayah> ayahs = null;
    @SerializedName("edition")
    @Expose
    private Edition edition;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishNameTranslation() {
        return englishNameTranslation;
    }

    public void setEnglishNameTranslation(String englishNameTranslation) {
        this.englishNameTranslation = englishNameTranslation;
    }

    public String getRevelationType() {
        return revelationType;
    }

    public void setRevelationType(String revelationType) {
        this.revelationType = revelationType;
    }

    public Integer getNumberOfAyahs() {
        return numberOfAyahs;
    }

    public void setNumberOfAyahs(Integer numberOfAyahs) {
        this.numberOfAyahs = numberOfAyahs;
    }

    public List<Ayah> getAyahs() {
        return ayahs;
    }

    public void setAyahs(List<Ayah> ayahs) {
        this.ayahs = ayahs;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

}
