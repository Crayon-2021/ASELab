package com.group6.aselab.dto;

import com.group6.aselab.entity.Flight;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightDto {
    public String flightNumber; // 航班号

    public String departureCity; // 出发地

    public String destinationCity; // 目的地

    public LocalDateTime departureTime; // 出发时间

    public String airlineCompany; // 航空公司

    public LocalDateTime arrivalTime; // 到达时间

    public BigDecimal firstPrice; // 头等舱价格

    public BigDecimal businessPrice; // 商务舱价格

    public BigDecimal economyPrice; // 经济舱价格

    public Integer firstClassSeatsCount; // 头等舱数量

    public Integer businessClassSeatsCount; // 商务舱数量

    public Integer economyClassSeatsCount; // 经济舱数量

    public String firstClassSeatsLayout; // 头等舱布局

    public String businessClassSeatsLayout; // 商务舱布局

    public String economyClassSeatsLayout; // 经济舱布局

    public List<SeatDto> seats = new ArrayList<>(); // 座位信息

    public static FlightDto from(Flight flight){
        FlightDto flightDto = new FlightDto();
        flightDto.flightNumber = flight.getFlightNumber();
        flightDto.departureCity = flight.getDepartureCity();
        flightDto.destinationCity = flight.getDestinationCity();
        flightDto.departureTime = flight.getDepartureTime();
        flightDto.airlineCompany = flight.getAirlineCompany();
        flightDto.arrivalTime = flight.getArrivalTime();
        flightDto.firstPrice = flight.getFirstPrice();
        flightDto.businessPrice = flight.getBusinessPrice();
        flightDto.economyPrice = flight.getEconomyPrice();
        flightDto.firstClassSeatsCount = flight.getAircraftModel().getFirstClassSeatsCount();
        flightDto.businessClassSeatsCount = flight.getAircraftModel().getBusinessClassSeatsCount();
        flightDto.economyClassSeatsCount = flight.getAircraftModel().getEconomyClassSeatsCount();
        flightDto.firstClassSeatsLayout = flight.getAircraftModel().getFirstClassSeatsLayout();
        flightDto.businessClassSeatsLayout = flight.getAircraftModel().getBusinessClassSeatsLayout();
        flightDto.economyClassSeatsLayout = flight.getAircraftModel().getEconomyClassSeatsLayout();
        flightDto.seats = flight.getSeats().stream().map(SeatDto::from).toList();
        return flightDto;
    }
}
