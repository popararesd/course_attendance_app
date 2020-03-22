package com.example.demo.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 * The class is basically just a regular User.
 *
 */
@Entity
@DiscriminatorValue("admin")
public class Admin extends User {
}
