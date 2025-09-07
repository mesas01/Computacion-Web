package com.proyecto.entrega.dto;

public class GatewayDTO {

    private Long id;
    private String tipo;
    private Long processId;

    // Constructores
    public GatewayDTO() {
    }

    public GatewayDTO(Long id, String tipo, Long processId) {
        this.id = id;
        this.tipo = tipo;
        this.processId = processId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }
}
