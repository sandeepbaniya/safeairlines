package edu.mum.cs.cs452.safeairlines.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor

public class Manager extends User {
    private String  title;



}
