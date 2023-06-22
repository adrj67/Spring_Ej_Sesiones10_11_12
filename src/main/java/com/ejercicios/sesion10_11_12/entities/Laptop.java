
package com.ejercicios.sesion10_11_12.entities;

//import jakarta.persistence.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import java.time.LocalDate;

@Entity
@Table (name="Laptops")
@ApiModel("Entidad Laptop de segunda seleccion.")
public class Laptop {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave id autoincremental tipo Long")
    private Long id;
    private String marca;
    private String procesador;
    private int ram;
    private int capacidadDiscoDuro;
    private boolean sistemaOperativo;
    @ApiModelProperty("Precio en dolares, con dos decimales utilizando . como separador")
    private Double price;
    @ApiModelProperty("Fecha de ingreso a stock.")
    private LocalDate releaseDate;

    public Laptop() {
    }

    public Laptop(Long id, String marca, String procesador, int ram, int capacidadDiscoDuro, boolean sistemaOperativo, Double price, LocalDate releaseDate) {
        this.id = id;
        this.marca = marca;
        this.procesador = procesador;
        this.ram = ram;
        this.capacidadDiscoDuro = capacidadDiscoDuro;
        this.sistemaOperativo = sistemaOperativo;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getCapacidadDiscoDuro() {
        return capacidadDiscoDuro;
    }

    public void setCapacidadDiscoDuro(int capacidadDiscoDuro) {
        this.capacidadDiscoDuro = capacidadDiscoDuro;
    }

    public boolean isSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(boolean sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    
}
