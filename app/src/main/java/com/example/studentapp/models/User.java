package com.example.studentapp.models;

public class User {

    private int id;
    private String unique_id,name,email,gender,admissionNumber,programe,phone,campus,faculty;

    public User(int id, String unique_id, String name, String email, String gender, String admissionNumber, String programe, String phone, String campus, String faculty) {
        this.id = id;
        this.unique_id = unique_id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.admissionNumber = admissionNumber;
        this.programe = programe;
        this.phone = phone;
        this.campus = campus;
        this.faculty = faculty;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getPrograme() {
        return programe;
    }

    public void setPrograme(String programe) {
        this.programe = programe;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
