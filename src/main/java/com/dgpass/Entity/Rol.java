package com.dgpass.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {
    @Id
    private Long id;
    private String nombre;

    public Rol() {
    }

}
