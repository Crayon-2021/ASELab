package com.group6.aselab.dto;

import com.group6.aselab.entity.ClassType;
import com.group6.aselab.entity.Seat;

public class SeatDto {
    public String seatNumber; // 座位号(例如16A、23C)

    public ClassType classType; // 座位类型(头等舱/商务舱/经济舱)

    public boolean isAvailable; // 是否可用

    public static SeatDto from(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.seatNumber = seat.getSeatNumber();
        seatDto.classType = seat.getClassType();
        seatDto.isAvailable = seat.isAvailable();
        return seatDto;
    }
}
