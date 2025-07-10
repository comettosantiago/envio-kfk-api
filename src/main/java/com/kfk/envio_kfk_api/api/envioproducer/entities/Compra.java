package com.kfk.envio_kfk_api.api.envioproducer.entities;

import com.kfk.envio_kfk_api.api.envioproducer.entities.envio.Envio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "compra", schema = "kfk")
public class Compra {
    @Id
    private Long id;
    private String description;
    private Double price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "envio_id", referencedColumnName = "id")
    private Envio envio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id", referencedColumnName = "id")
    private Factura factura;


    public Compra() {
    }

    public Compra(String description, Double price) {
        this.description = description;
        this.price = price;
    }


}
