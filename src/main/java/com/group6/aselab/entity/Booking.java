package com.group6.aselab.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId; // 订座id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 对应用户

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight; // 对应航班

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice; // 订座价格

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.CONFIRMED; // 订座状态(确认/取消)

    @Column(name = "booking_time")
    private LocalDateTime bookingTime; // 订座时间

    @Column(name = "cancellation_time")
    private LocalDateTime cancellationTime; // 取消时间

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat; // 对应座位

    public void setSeat(Seat seat) {
        this.seat = seat;
        seat.setBooking(this);
    }

    // 业务方法
    public void cancel() {
        this.status = BookingStatus.CANCELLED;
        this.cancellationTime = LocalDateTime.now();
        if (this.seat != null) {
            this.seat.setAvailable(true);
        }
    }
}