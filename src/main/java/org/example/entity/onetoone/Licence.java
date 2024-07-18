package org.example.entity.onetoone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "licences")
public class Licence {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

//  @OneToOne(mappedBy = "licences")
//  public Employee employee;

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
