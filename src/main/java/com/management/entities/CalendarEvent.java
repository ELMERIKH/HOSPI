package com.management.entities;

import java.time.LocalDateTime;

public class CalendarEvent {
    private Long id;
    private String title;
    public CalendarEvent() {
    }

    public CalendarEvent(String title, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.title = title;
        this.start = startDateTime;
        this.endDateTime = endDateTime;
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

    public LocalDateTime getStartDateTime() {
        return start;
    }

    public void setStartDateTime(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private LocalDateTime start;
    private LocalDateTime endDateTime;
    private String color;

    // constructors, getters, and setters
}