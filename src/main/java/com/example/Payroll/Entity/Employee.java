package com.example.Payroll.Entity;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_sequence")
    @SequenceGenerator(name = "emp_sequence")
    Long id;
    String name;
    String lname;
    boolean isAdmin;
    String email;
    String contact;
    double salary;
    double hra;
    double ta;
    double a1;
    double a2;
    String designation;
    String address;
    String city;
    String country;
    String exe;
    String password;



    public Employee() {
    }



    public Employee(String name, String lname, boolean isAdmin, String email, String contact, double salary, double hra, double ta, double a1, double a2, String password, Date date,String designation, String address, String city, String country, String exe) {

        this.name = name;
        this.lname = lname;
        this.isAdmin = isAdmin;
        this.email = email;
        this.contact = contact;
        this.salary = salary;
        this.hra = hra;
        this.ta = ta;
        this.a1 = a1;
        this.a2 = a2;
        this.password=password;
        this.designation = designation;
        this.address = address;
        this.city = city;
        this.country = country;
        this.exe = exe;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public double getHra() {
        return hra;
    }

    public void setHra(double hra) {
        this.hra = hra;
    }

    public double getTa() {
        return ta;
    }

    public void setTa(double ta) {
        this.ta = ta;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExe() {
        return exe;
    }

    public void setExe(String exe) {
        this.exe = exe;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lname='" + lname + '\'' +
                ", isAdmin=" + isAdmin +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", salary=" + salary +
                ", hra=" + hra +
                ", ta=" + ta +
                ", a1=" + a1 +
                ", a2=" + a2 +
                ", designation='" + designation + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", exe='" + exe + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

