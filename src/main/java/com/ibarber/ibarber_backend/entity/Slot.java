package com.ibarber.ibarber_backend.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name ="slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private boolean booked = false;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    public User getClient() {
        return client;
    }
    public void setClient(User client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private User barber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public User getBarber() {
        return barber;
    }

    public void setBarber(User barber) {
        this.barber = barber;
    }

}
