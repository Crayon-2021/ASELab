package com.group6.aselab.dto;

import com.group6.aselab.entity.Booking;
import com.group6.aselab.entity.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDto {
    public String flightNumber; // 对应航班

    public BigDecimal totalPrice; // 订座价格

    public BookingStatus status = BookingStatus.CONFIRMED; // 订座状态(确认/取消)

    public LocalDateTime bookingTime; // 订座时间

    public LocalDateTime cancellationTime; // 取消时间

    public String seatNumber; // 对应座位

    public static BookingDto from(Booking booking){
        BookingDto bookingDto = new BookingDto();
        bookingDto.flightNumber = booking.getFlight().getFlightNumber();
        bookingDto.totalPrice = booking.getTotalPrice();
        bookingDto.status = booking.getStatus();
        bookingDto.bookingTime = booking.getBookingTime();
        bookingDto.cancellationTime = booking.getCancellationTime();
        bookingDto.seatNumber = booking.getSeat().getSeatNumber();
        return bookingDto;
    }
}
