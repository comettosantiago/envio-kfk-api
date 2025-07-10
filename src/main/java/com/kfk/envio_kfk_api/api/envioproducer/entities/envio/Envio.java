package com.kfk.envio_kfk_api.api.envioproducer.entities.envio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "envio", schema = "kfk")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long compraId;
    private String trackingCode;
    private boolean isDelivered;

    public Envio() {
    }

    public Envio(Long compraId, String trackingCode, boolean isDelivered) {
        this.compraId = compraId;
        this.trackingCode = trackingCode;
        this.isDelivered = isDelivered;
    }

}