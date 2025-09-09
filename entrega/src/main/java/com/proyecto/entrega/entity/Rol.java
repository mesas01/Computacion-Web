package com.proyecto.entrega.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@Entity// Marca la clase como entidad JPA (se mapeará a una tabla "rol")
@Table(name = "rol") // Fija el nombre de tabla explícitamente
@Where(clause = "status = 'active'") // Soft delete: solo trae registros activos en consultas normales
@SQLDelete(sql = "UPDATE rol SET status = 'inactive' WHERE id = ?") // Al borrar, marca 'inactive' en vez de borrar físico
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// ID autoincremental

    @Column(nullable = false)// No se puede dejar vacío en la BD
    private String nombre;// Nombre del rol (ej: "Administrador")

    @Column
    private String descripcion; // Texto opcional explicando el rol

    // Muchos roles pertenecen a una empresa (N:1). LAZY evita traer empresa si no se usa.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)// Columna FK obligatoria en la tabla rol
    private Empresa empresa;

    // Un rol puede estar en muchas actividades.
    //JPA solo traerá los datos del Rol (su id, nombre y descripción).
    // Las propiedades empresa y activities se quedarán "vacías"
    // Solo cuando se intente acceder a ellas por primera vez
    //por ejemplo, con miRol.getEmpresa() JPA ejecutará la consulta a la
    // base de datos para traer esa información.
    @OneToMany(mappedBy = "rolResponsable", fetch = FetchType.LAZY)
    private List<Activity> activities;

    // varios usuarios pueden tener el mismo rol.
    // En Usuario.java existe el campo 'rol', por eso el mappedBy apunta a "rol".
    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    // Soft delete
    @Column(nullable = false, length = 16)
    private String status = "active";
}

//su @SQLDelete apunta a activity, no a role en la version de ellos
//su codigo permite roles sin nombre o sin empresa, algo poco realista