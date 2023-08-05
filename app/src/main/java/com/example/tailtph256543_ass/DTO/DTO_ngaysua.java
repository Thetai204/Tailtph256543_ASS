package com.example.tailtph256543_ass.DTO;

public class DTO_ngaysua {
    private int id;
    private String ngaySua;
    private int id_user;

    public DTO_ngaysua() {
    }

    public DTO_ngaysua(int id, String ngaySua, int id_user) {
        this.id = id;
        this.ngaySua = ngaySua;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
