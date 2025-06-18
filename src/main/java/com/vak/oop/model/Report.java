package com.vak.oop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "reportId", columnDefinition = "UUID DEFAULT gen_random_uuid()")
  private UUID reportId;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;

  @Column(name = "rpName", nullable = false, length = 100)
  private String rpName;

  @Column(name = "rpInfo", nullable = false, columnDefinition = "TEXT")
  private String rpInfo;
}
