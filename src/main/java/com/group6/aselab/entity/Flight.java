package com.group6.aselab.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight { // 航班
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId; // 航班id

    @Column(name = "flight_number", nullable = false, length = 20)
    private String flightNumber; // 航班号

    @Column(name = "departure_city", nullable = false, length = 50)
    private String departureCity; // 出发地

    @Column(name = "destination_city", nullable = false, length = 50)
    private String destinationCity; // 目的地

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime; // 出发时间

    @Column(name = "airline_company", nullable = false)
    private String airlineCompany; // 航空公司

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime; // 到达时间

    @Column(name = "first_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal firstPrice; // 头等舱价格

    @Column(name = "business_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal businessPrice; // 商务舱价格

    @Column(name = "economy_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal economyPrice; // 经济舱价格

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 创建时间

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 更新时间

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private AircraftModel aircraftModel; // 飞机机型

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats = new ArrayList<>(); // 座位信息

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>(); // 订座信息

    public void addSeat(Seat seat) {
        seats.add(seat);
        seat.setFlight(this);
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
        seat.setFlight(null);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setFlight(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setFlight(null);
    }
}