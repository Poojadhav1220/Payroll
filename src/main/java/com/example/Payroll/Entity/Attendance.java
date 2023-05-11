package com.example.Payroll.Entity;


import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_sequence")
    @SequenceGenerator(name = "emp_sequence")

    long id;
    long idEmp;
    int status; //0=present, 1=halfday, 2=absent

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @UpdateTimestamp
    LocalDate date;


    public Attendance() {
    }

    public Attendance(long idEmp, int status) {
        this.idEmp = idEmp;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(long idEmp) {
        this.idEmp = idEmp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", idEmp=" + idEmp +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
