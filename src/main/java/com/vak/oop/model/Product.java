package com.vak.oop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "pdId", columnDefinition = "UUID DEFAULT gen_random_uuid()")
  private UUID pdId;
  @Column(name = "pdName", nullable = false, unique = true, length = 100)
  private String pdName;
  @Column(name = "pdPrice", nullable = false, precision = 12, scale = 2)
  private BigDecimal pdPrice;
  @Column(name = "pdInfo", nullable = false)
  private String pdInfo;
  @Column(name = "pdQuantity", nullable = false)
  private int pdQuantity;
  @ManyToOne
  @JoinColumn(name = "categoryId", nullable = false)
  private Category category;
}