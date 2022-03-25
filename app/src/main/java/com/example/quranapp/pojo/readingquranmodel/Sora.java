package com.example.quranapp.pojo.readingquranmodel;

public class Sora {

    int soraNumber, startPage, endPage;

    String arabicName, englishName,ayaText,ayaTextEmlaey;

    public int getSoraNumber() {
        return soraNumber;
    }

    public void setSoraNumber(int soraNumber) {
        this.soraNumber = soraNumber;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public String getAyaText() {
        return ayaText;
    }

    public void setAyaText(String ayaText) {
        this.ayaText = ayaText;
    }

    public String getAyaTextEmlaey() {
        return ayaTextEmlaey;
    }

    public void setAyaTextEmlaey(String ayaTextEmlaey) {
        this.ayaTextEmlaey = ayaTextEmlaey;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
}
