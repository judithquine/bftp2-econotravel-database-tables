package com.econotravel.api;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="experiences")
public class Experience{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "experience")
    private Set<Category> categories;

    public Experience() {
    }

    public Experience(String name) {
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

    public Set<Category> getCategory() {
        return categories;
    }

    public void setCategories(){
        this.categories = categories;
    }
}
