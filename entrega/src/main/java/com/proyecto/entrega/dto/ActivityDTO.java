package com.proyecto.entrega.dto;

public class ActivityDTO {

    private Long id;
    private String name;
    private String description;
    private String tipo;
    private String status;
    private Long processId;
    private Long rolResponsableId;

    // Constructores
    public ActivityDTO() {
    }

    public ActivityDTO(Long id, String name, String description, String tipo, String status, Long processId, Long rolResponsableId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tipo = tipo;
        this.status = status;
        this.processId = processId;
        this.rolResponsableId = rolResponsableId;
    }

    // Getters y Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getRolResponsableId() {
        return rolResponsableId;
    }

    public void setRolResponsableId(Long rolResponsableId) {
        this.rolResponsableId = rolResponsableId;
    }
}
