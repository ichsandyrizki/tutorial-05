package com.apap.tu05.service;

import com.apap.tu05.model.FlightModel;
import com.apap.tu05.repository.FlightDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDb flightDb;

    @Override
    public void addFlight(FlightModel flight) {
        flightDb.save(flight);

    }

    @Override
    public List<FlightModel> findAllFlightByPilotLicenseNumber(String licenseNumber) {
        return flightDb.findByPilotLicenseNumber(licenseNumber);
    }

    @Override
    public FlightModel findById(Long id) {
        return flightDb.findById(id).get();
    }

    @Override
    public FlightModel findByFlightNumber(String flightNumber) {
        return flightDb.findByFlightNumber(flightNumber).get();
    }

    @Override
    public void deleteFlight(Long id) {

        flightDb.removeById(id);
    }

    @Override
    public FlightModel updateFlight(FlightModel flightModel) {
        FlightModel targetFlight = flightDb.findById(flightModel.getId()).get();

        targetFlight.setDestination(flightModel.getDestination());
        targetFlight.setOrigin(flightModel.getOrigin());
        targetFlight.setFlightNumber(flightModel.getFlightNumber());
        targetFlight.setTime(flightModel.getTime());
        flightDb.save(targetFlight);

        return targetFlight;
    }

    @Override
    public List<FlightModel> flightList() {
        return flightDb.findAll();
    }
}
