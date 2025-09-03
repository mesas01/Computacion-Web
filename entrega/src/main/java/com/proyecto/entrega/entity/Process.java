package com.proyecto.entrega.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< Updated upstream
@Where(clause = "status = 'active'")
@SQLDelete(sql = "UPDATE process SET status = 'inactive' WHERE id = ?")
=======
@Where(clause = "status = 0")
@SQLDelete(sql = "UPDATE activity SET status = 1 WHERE id = ?")
>>>>>>> Stashed changes

public class Process{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; 
    private String description;
    private String status;

}
<<<<<<< Updated upstream





=======
>>>>>>> Stashed changes
