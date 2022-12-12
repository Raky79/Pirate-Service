package com.example.pirateservice.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity  // lets Hibernate know that we want to map this class to a database table.
@Table(name = "ships")   // tells Hibernate the name of the table we want to map to.
public class Ship {

    @Id   // we tell hibernate which property we want it to use for the primary key by using the @Id annotation
    @GeneratedValue   // We are also going to tell it to generate the value for the id primary key using the IDENTITY strategy.
    private Long id;  //  uses an auto-incremented value generated by the database.

    @Column(name = "name")
    private String name;

    @JsonIgnoreProperties({"ship"})   // This tells the serializer to not try and convert these properties to JSON.
    @OneToMany(mappedBy = "ship")   //  @OneToMany takes an argument mappedBy which needs to have the name of the property in Pirate class that we wish to use as the foreign key.
    private List<Pirate> pirates;  // we use @OneToMany to annotate the List of Pirate in the Ship class.

    public Ship(String name) {
        this.name = name;
        this.pirates = new ArrayList<>();
    }

    public Ship() {}

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

    public List<Pirate> getPirates() {
        return pirates;
    }

    public void setPirates(List<Pirate> pirates) {
        this.pirates = pirates;
    }


}
