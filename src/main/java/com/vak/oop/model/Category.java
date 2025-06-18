package com.vak.oop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "categoryId", columnDefinition = "UUID DEFAULT gen_random_uuid()")
  private UUID categoryId;
  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;
}