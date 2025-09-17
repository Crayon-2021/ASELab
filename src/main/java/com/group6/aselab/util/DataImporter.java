package com.group6.aselab.util;

import com.group6.aselab.entity.*;
import com.group6.aselab.repository.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class DataImporter implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AircraftModelRepository aircraftModelRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始导入初始数据...");

        // 导入用户数据
        importUsers();

        // 导入飞机布局数据
        importAircraftModels();

        // 导入航班数据
        importFlights();

        // 初始化座位数据
        initializeSeats();

        log.info("初始数据导入完成！");
    }

    private void importUsers() {
        if (userRepository.count() > 0) {
            log.info("用户数据已存在，跳过导入");
            return;
        }
        
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");


        User user2 = new User();
        user2.setUsername("zhangsan");
        user2.setPassword("zhangsan");


        User user3 = new User();
        user3.setUsername("lisi");
        user3.setPassword("lisi");

        List<User> users = Arrays.asList(
                user1,
                user2,
                user3
        );

        userRepository.saveAll(users);

        log.info("成功导入 {} 个用户", users.size());
    }

    private void importAircraftModels() {
        if (aircraftModelRepository.count() > 0) {
            log.info("飞机布局数据已存在，跳过导入");
            return;
        }

        // 波音737布局配置
        AircraftModel boeing737 = new AircraftModel();
        boeing737.setModelName("波音737");
        boeing737.setFirstClassSeatsCount(16);
        boeing737.setFirstClassSeatsLayout("AB-CD");
        boeing737.setBusinessClassSeatsCount(48);
        boeing737.setBusinessClassSeatsLayout("AB-CD-EF");
        boeing737.setEconomyClassSeatsCount(150);
        boeing737.setEconomyClassSeatsLayout("ABC-DEF");

        // 空客A320布局配置
        AircraftModel airbusA320 = new AircraftModel();
        airbusA320.setModelName("空客A320");
        airbusA320.setFirstClassSeatsCount(24);
        airbusA320.setFirstClassSeatsLayout("AB-CD");
        airbusA320.setEconomyClassSeatsCount(144);
        airbusA320.setEconomyClassSeatsLayout("ABC-DEF");

        // 波音787布局配置
        AircraftModel boeing787 = new AircraftModel();
        boeing787.setModelName("波音787");
        boeing787.setFirstClassSeatsCount(32);
        boeing787.setFirstClassSeatsLayout("AB-EF");
        boeing787.setBusinessClassSeatsCount(72);
        boeing787.setBusinessClassSeatsLayout("AB-CD-EF");
        boeing787.setEconomyClassSeatsCount(270);
        boeing787.setEconomyClassSeatsLayout("ABC-DEF-GHI");

        List<AircraftModel> models = Arrays.asList(
                boeing737,
                airbusA320,
                boeing787
        );

        aircraftModelRepository.saveAll(models);
        log.info("成功导入 {} 个飞机布局", models.size());
    }

    private void importFlights() {
        if (flightRepository.count() > 0) {
            log.info("航班数据已存在，跳过导入");
            return;
        }

        List<AircraftModel> models = aircraftModelRepository.findAll();
        if (models.isEmpty()) {
            log.warn("没有可用的飞机布局，无法创建航班");
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        Flight flight1 = new Flight();
        flight1.setFlightNumber("CA1234");
        flight1.setAirlineCompany("中国国际航空");
        flight1.setAircraftModel(models.get(0));
        flight1.setDepartureCity("北京");
        flight1.setDestinationCity("上海");
        flight1.setDepartureTime(now.plusDays(1).withHour(8).withMinute(0));
        flight1.setArrivalTime(now.plusDays(1).withHour(10).withMinute(30));
        flight1.setFirstPrice(new BigDecimal("1200.00"));
        flight1.setBusinessPrice(new BigDecimal("1000.00"));
        flight1.setEconomyPrice(new BigDecimal("700.00"));

        Flight flight2 = new Flight();
        flight2.setFlightNumber("MU5678");
        flight2.setAirlineCompany("中国东方航空");
        flight2.setAircraftModel(models.get(1));
        flight2.setDepartureCity("上海");
        flight2.setDestinationCity("广州");
        flight2.setDepartureTime(now.plusDays(1).withHour(14).withMinute(0));
        flight2.setArrivalTime(now.plusDays(1).withHour(16).withMinute(30));
        flight2.setFirstPrice(new BigDecimal("980.00"));
        flight2.setBusinessPrice(new BigDecimal("800.00"));
        flight2.setEconomyPrice(new BigDecimal("520.00"));

        Flight flight3 = new Flight();
        flight3.setFlightNumber("CZ9012");
        flight3.setAirlineCompany("中国南方航空");
        flight3.setAircraftModel(models.get(0));
        flight3.setDepartureCity("广州");
        flight3.setDestinationCity("深圳");
        flight3.setDepartureTime(now.plusDays(2).withHour(9).withMinute(30));
        flight3.setArrivalTime(now.plusDays(2).withHour(10).withMinute(45));
        flight3.setFirstPrice(new BigDecimal("600.00"));
        flight3.setBusinessPrice(new BigDecimal("460.00"));
        flight3.setEconomyPrice(new BigDecimal("380.00"));

        Flight flight4 = new Flight();
        flight4.setFlightNumber("CA888");
        flight4.setAirlineCompany("中国国际航空");
        flight4.setAircraftModel(models.get(2));
        flight4.setDepartureCity("北京");
        flight4.setDestinationCity("纽约");
        flight4.setDepartureTime(now.plusDays(3).withHour(13).withMinute(0));
        flight4.setArrivalTime(now.plusDays(3).withHour(16).withMinute(0).plusHours(13));
        flight4.setFirstPrice(new BigDecimal("8800.00"));
        flight4.setBusinessPrice(new BigDecimal("7100.00"));
        flight4.setEconomyPrice(new BigDecimal("5300.00"));

        Flight flight5 = new Flight();
        flight5.setFlightNumber("MU589");
        flight5.setAirlineCompany("中国东方航空");
        flight5.setAircraftModel(models.get(2));
        flight5.setDepartureCity("上海");
        flight5.setDestinationCity("洛杉矶");
        flight5.setDepartureTime(now.plusDays(4).withHour(15).withMinute(30));
        flight5.setArrivalTime(now.plusDays(4).withHour(19).withMinute(0).plusHours(12));
        flight5.setFirstPrice(new BigDecimal("9200.00"));
        flight5.setBusinessPrice(new BigDecimal("8200.00"));
        flight5.setEconomyPrice(new BigDecimal("6000.00"));

        List<Flight> flights = Arrays.asList(flight1, flight2, flight3, flight4, flight5);

        flightRepository.saveAll(flights);
        log.info("成功导入 {} 个航班", flights.size());
    }

    private void initializeSeats() {
        if (seatRepository.count() > 0) {
            log.info("座位数据已存在，跳过初始化");
            return;
        }

        List<Flight> flights = flightRepository.findAll();
        if (flights.isEmpty()) {
            log.warn("没有可用的航班，无法初始化座位");
            return;
        }

        int totalSeatsCreated = 0;
        for (Flight flight : flights) {
            AircraftModel model = flight.getAircraftModel();
            if (model == null) {
                log.warn("航班 {} 没有关联的飞机布局，跳过座位初始化", flight.getFlightNumber());
                continue;
            }

            List<Seat> seats = new ArrayList<>();

            seats.addAll(generateSeats(flight, ClassType.FIRST, model.getFirstClassSeatsCount(),
                    model.getFirstClassSeatsLayout(), 1) );

            seats.addAll(generateSeats(flight, ClassType.BUSINESS, model.getBusinessClassSeatsCount(),
                    model.getBusinessClassSeatsLayout(), model.getRows(ClassType.FIRST) + 1) );

            seats.addAll(generateSeats(flight, ClassType.ECONOMY, model.getEconomyClassSeatsCount(),
                    model.getEconomyClassSeatsLayout(), model.getRows(ClassType.FIRST) + model.getRows(ClassType.BUSINESS) + 1) );

            totalSeatsCreated += seats.size();

            seatRepository.saveAll(seats);

            flight.setSeats(seats);
            flightRepository.save(flight);
        }

        log.info("总共初始化了 {} 个座位", totalSeatsCreated);
    }

    private List<Seat> generateSeats(Flight flight, ClassType classType, int totalCount, String layout, int startRow) {
        List<Seat> seats = new ArrayList<>();

        if (totalCount == 0) return seats;

        String layoutWithoutSeparator = layout.replace("-", "");
        int seatsPerRow = layoutWithoutSeparator.length();

        int rows = totalCount / seatsPerRow;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                Seat seat = new Seat();
                seat.setSeatNumber(String.valueOf(i + startRow) + layoutWithoutSeparator.charAt(j));
                seat.setFlight(flight);
                seat.setClassType(classType);
                seats.add(seat);
            }
        }

        return seats;
    }
}