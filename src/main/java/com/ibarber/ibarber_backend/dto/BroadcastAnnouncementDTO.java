package com.ibarber.ibarber_backend.dto;

public class BroadcastAnnouncementDTO {
    private Long id;
    private String title;
    private String message;
    private String targetGroup;
    private String date;
    private Long createdBy;

    public BroadcastAnnouncementDTO() {
    }

    public BroadcastAnnouncementDTO(Long id, String title, String message, String targetGroup, String date, Long createdBy) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.targetGroup = targetGroup;
        this.date = date;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
