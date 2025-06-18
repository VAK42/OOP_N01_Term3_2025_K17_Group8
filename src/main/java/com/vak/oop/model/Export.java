package com.vak.oop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "export")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Export {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "epId", columnDefinition = "UUID DEFAULT gen_random_uuid()")
  private UUID epId;

  @ManyToOne
  @JoinColumn(name = "pdId", nullable = false)
  private Product product;

  @Column(name = "pdPrice", nullable = false)
  private BigDecimal pdPrice;

  @Column(name = "pdQuantity", nullable = false)
  private int pdQuantity;

  @Column(name = "pdTotalPrice", insertable = false, updatable = false)
  private BigDecimal pdTotalPrice;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @Column(name = "date", nullable = false)
  private LocalDateTime date = LocalDateTime.now();
}
