package com.management.services;

import com.management.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorNotificationServiceImpl implements NotificationService {
    private List<String> notifications = new ArrayList<>();

    @Override
    public void sendNotification(String message) {
        notifications.add(message);
        System.out.println("Doctor received a new notification: " + message);
    }

    public List<String> getNotifications() {
        return notifications;
    }
}






