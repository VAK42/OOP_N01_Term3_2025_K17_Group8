package com.vak.oop.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "import")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Import {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ipId", columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID ipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdId", nullable = false)
    private Product product;

    @Column(name = "pdPrice", nullable = false)
    private BigDecimal pdPrice;

    @Column(name = "pdQuantity", nullable = false)
    private int pdQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();
}
