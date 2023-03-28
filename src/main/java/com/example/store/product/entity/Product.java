package com.example.store.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Product {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Double stock;

    private Double price;

    private String status;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    // siempre se mira de la clase a la que llamamos a esta, MUCHAS CATEGORIA PUEDEN TENER UN PRODUCTO
    @ManyToOne(fetch = FetchType.LAZY) // LAZY SOLO CARGA LOS ELEMENTOS QUE SE NECESITEN, en cambio Eager carga todos los elementos aunque no se utilicen
    @JoinColumn(name = "category_id")
    @Nullable
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;

}
