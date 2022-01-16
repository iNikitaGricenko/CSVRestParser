package com.wolfhack.csvrest.data.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CSVModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String state;
    private String name;
    private String institutionType;
    private String phone;
    private String website;

    public CSVModel(String state, String name, String institutionType, String phone, String website) {
        this.state = state;
        this.name = name;
        this.institutionType = institutionType;
        this.phone = phone;
        this.website = website;
    }

    public CSVModel() {
    }
}
