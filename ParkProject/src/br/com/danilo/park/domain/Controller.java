package br.com.danilo.park.domain;

import javax.swing.*;
import java.time.LocalDateTime;

public class Controller {
    private long id;
    private String license;
    private String model;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;


    private Double Total;

    public Controller() {
    }

    public Controller(long id, String license, String model, LocalDateTime timeIn, LocalDateTime timeOut) {
        this.id = id;
        this.license = license;
        this.model = model;
        this.dateIn = timeIn;
        this.dateOut = timeOut;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {this.license = license;}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDateTime dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDateTime getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDateTime dateOut) {
        this.dateOut = dateOut;
    }
}
