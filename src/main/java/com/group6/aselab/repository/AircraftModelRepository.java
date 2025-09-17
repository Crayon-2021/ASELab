package com.group6.aselab.repository;

import com.group6.aselab.entity.AircraftModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftModelRepository extends JpaRepository<AircraftModel, Integer> {
}
