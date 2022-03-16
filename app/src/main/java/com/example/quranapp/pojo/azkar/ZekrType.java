package com.example.quranapp.pojo.azkar;

import java.util.Objects;

public class ZekrType {

   private String zekrName;



    public ZekrType(String zekrName) {
        this.zekrName = zekrName;
    }


    public String getZekrName() {
        return zekrName;
    }

    // equals and hashCode methods are used for hashSet for prevent duplicated items
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZekrType)) return false;
        ZekrType zekrType = (ZekrType) o;
        return getZekrName().equals(zekrType.getZekrName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getZekrName());
    }
}
