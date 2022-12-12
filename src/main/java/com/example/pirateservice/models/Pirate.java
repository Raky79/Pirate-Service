package com.example.pirateservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pirates")
public class Pirate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // all the properties (first_name, age ..) need to have their own column
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    // new Pirate("John", "Silver", 30)

    @ManyToOne // should be used to annotate the property in our class that points back to the class that stores many of it.
    //we use it to annotate the ship property in Pirate. Since a Pirate needs to point back to its Ship. Also,
    // because we need to tell JPA that we know pirates table needs to have a foreign key for ships.
    @JoinColumn(name = "ship_id", nullable = false) // this will generate a column ship_id (a foreign key) in the pirates table which will point
    // to the id of the ships table (primary key). // To ensure that each pirate needs to have a ship we will set the foreign key as nullable = false.
    @JsonIgnoreProperties({"pirates"})   // This tells the serializer to not try and convert these properties to JSON.
    private Ship ship;

    @ManyToMany
    @JsonIgnoreProperties({"pirates"})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)  //  we can use the @Cascade setting to allow or disallow
    // the delete action.If for example we set it to SAVE_UPDATE then only
    // save and update actions are cascaded to the other side of the relationship.
    // @JoinTable is required to setup the join table
    @JoinTable(
            name = "pirates_raids",
            joinColumns = { @JoinColumn(     //  To indicate which columns we want in the join table, a @JoinColumn must be passed into the @JoinTable via the joinColumn argument
                    name = "pirate_id",   // in Pirate we will give the JoinTable a JoinColumn with name pirate_id.
                    nullable = false,
                    updatable = false)
            },
            inverseJoinColumns = { @JoinColumn(  // We must also map the reverse with the inverseJoinColumns argument to @JoinTable
                    name = "raid_id",    //this is from the point of view of the class we are "in", so for Pirate this would be raid_id
                    nullable = false,      // We don't want these columns in the join table to be allowed to be set to null.
                    updatable = false)    // Also, it's not desired that they should be able to be individually updated.
                                          // it just means they cannot be individually changed once created.
            }
    )
    private List<Raid> raids;



    public Pirate(String firstName, String lastName, int age, Ship ship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.ship = ship;   // to ensure Pirate must have a ship we will also change the constructor of Pirate to take in the ship when we create a new Pirate object.
        this.raids = new ArrayList<>();
    }

    //new Pirate()     --- default empty constructor that gives us a new pirate
    public Pirate() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void addRaid(Raid raid){
        this.raids.add(raid);
    }
}
