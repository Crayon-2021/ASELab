package com.group6.aselab.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "aircraft_models")
public class AircraftModel { // 飞机机型，以此为模板生成航班的座位信息
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelId; // 机型id

    @Column(name = "model_name", nullable = false, unique = true, length = 30)
    private String modelName; // 机型名称

    @Column(name = "first_class_seats_count", nullable = false)
    private Integer firstClassSeatsCount = 0; // 头等舱座位数

    @Column(name = "first_class_seats_layout")
    private String firstClassSeatsLayout = ""; // 头等舱座位布局(例如"ABC-JKL")，每一行的座位数量蕴含在其中

    @Column(name = "business_class_seats_count", nullable = false)
    private Integer businessClassSeatsCount = 0; // 商务舱座位数

    @Column(name = "business_class_seats_layout")
    private String businessClassSeatsLayout = ""; // 商务舱座位布局(例如"ABC-JKL")，每一行的座位数量蕴含在其中

    @Column(name = "economy_class_seats_count", nullable = false)
    private Integer economyClassSeatsCount = 0; // 经济舱座位数

    @Column(name = "economy_class_seats_layout")
    private String economyClassSeatsLayout = ""; // 经济舱座位布局(例如"ABC-JKL")，每一行的座位数量蕴含在其中

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "aircraftModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flight> flights = new ArrayList<>(); // 对应航班

    public int getSeatsPerRow(ClassType classType){
        return switch (classType) {
            case FIRST -> firstClassSeatsLayout.replace("-", "").length();
            case BUSINESS -> businessClassSeatsLayout.replace("-", "").length();
            case ECONOMY -> economyClassSeatsLayout.replace("-", "").length();
        };
    }

    public int getRows(ClassType classType){
        int seatsPerRow = getSeatsPerRow(classType);
        if (seatsPerRow == 0) return 0;
        return switch (classType) {
            case FIRST -> firstClassSeatsCount / seatsPerRow;
            case BUSINESS -> businessClassSeatsCount / seatsPerRow;
            case ECONOMY -> economyClassSeatsCount / seatsPerRow;
        };
    }
}