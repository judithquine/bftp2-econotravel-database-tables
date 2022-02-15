package com.econotravel.api;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "experience_id", nullable = false)
    private Experience experience;

    public Category() {
    }

    public Category(long c, String name) {
    }

    public Category(String name) {
        this.name = name;
    }

      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
