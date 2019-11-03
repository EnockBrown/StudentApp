package com.example.studentapp.models;

public class Units {
    private String unique_id,name,code;

    public Units(String unique_id, String name, String code) {
        this.unique_id = unique_id;
        this.name = name;
        this.code = code;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
