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
@SQLDelete(sql = "UPDATE edge SET status = 'inactive' WHERE id = ?")
=======
@Where(clause = "status = 0")
@SQLDelete(sql = "UPDATE activity SET status = 1 WHERE id = ?")
>>>>>>> Stashed changes

public class Edge{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String status;
    

    @ManyToOne
    @JoinColumn(name = "process_id")
<<<<<<< Updated upstream
    private Process process;

    @ManyToOne
    @JoinColumn(name = "activity_source_id")
    private Activity activitySource;

    @ManyToOne
    @JoinColumn(name = "activity_destiny_id")
    private Activity activityDestiny;
=======
    private Process process_id;

    @ManyToOne
    @JoinColumn(name = "activity_source_id")
    private Activity activity_source;

    @ManyToOne
    @JoinColumn(name = "activity_destiny_id")
    private Activity activity_destiny;
>>>>>>> Stashed changes

}





