package com.proyecto.entrega.entity;

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@SQLDelete(sql = "UPDATE activity SET status = 'inactive' WHERE id = ?")
=======
@Where(clause = "status = 0")
@SQLDelete(sql = "UPDATE activity SET status = 1 WHERE id = ?")
>>>>>>> Stashed changes

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double x;
    private Double y;
    private String description;
    private Double width;
    private Double height;
    private String status;

    @ManyToOne
    @JoinColumn(name = "process_id")
<<<<<<< Updated upstream
    private Process process;
=======
    private Process process_id;
>>>>>>> Stashed changes

}




