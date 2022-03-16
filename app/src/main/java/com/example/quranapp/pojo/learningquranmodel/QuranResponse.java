package com.example.quranapp.pojo.learningquranmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuranResponse {
        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("data")
        @Expose
        private List<Quran> data = null;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Quran> getData() {
            return data;
        }

        public void setData(List<Quran> data) {
            this.data = data;
        }
}
