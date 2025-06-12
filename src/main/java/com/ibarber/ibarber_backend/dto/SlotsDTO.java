package com.ibarber.ibarber_backend.dto;
import java.time.LocalDate;
import java.time.LocalTime;

public class SlotsDTO {
    private Long id; // must have Id
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Long barberId;
    private Long clientId;

    private boolean approveAppointment;

    public boolean isApproveAppointment() {
        return approveAppointment;
    }

    public void setApproveAppointment(boolean approveAppointment) {
        this.approveAppointment = approveAppointment;
    }
    public Long getClientId() {
        return clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getBarberId() {
        return barberId;
    }

    public void setBarberId(Long barberId) {
        this.barberId = barberId;
    }

}
