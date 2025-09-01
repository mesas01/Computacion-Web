package com.proyecto.entrega.dto;

public class EdgeDTO {
    private Long id;
    private Long sourceId;
    private Long targetId;
    private String sourceType;
    private String targetType;
    private Long processId;

    // Constructores
    public EdgeDTO() {
    }

    public EdgeDTO(Long id, Long sourceId, Long targetId, String sourceType, String targetType, Long processId) {
        this.id = id;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.sourceType = sourceType;
        this.targetType = targetType;
        this.processId = processId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }
}
