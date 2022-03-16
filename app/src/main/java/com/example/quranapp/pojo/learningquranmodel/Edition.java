package com.example.quranapp.pojo.learningquranmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Edition {
    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("englishName")
    @Expose
    private String englishName;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("direction")
    @Expose
    private Object direction;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getDirection() {
        return direction;
    }

    public void setDirection(Object direction) {
        this.direction = direction;
    }
}
