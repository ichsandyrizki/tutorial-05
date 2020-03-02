package com.apap.tu05.service;

import com.apap.tu05.model.FlightModel;
import java.util.List;

public interface FlightService {
    void addFlight (FlightModel flight);
    List<FlightModel> findAllFlightByPilotLicenseNumber(String licenseNumber);
    FlightModel findById (Long id);
    FlightModel findByFlightNumber(String flightNumber);
    void deleteFlight(Long id);
    FlightModel updateFlight(FlightModel flightModel);
    List<FlightModel> flightList();
}