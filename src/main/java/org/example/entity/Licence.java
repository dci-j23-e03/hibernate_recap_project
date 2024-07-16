package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "licences")
public class Licence {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  public Licence() {
  }

  public Licence(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Licence{" +
        "id=" + id +
        ", description='" + description + '\'' +
        '}';
  }
}
