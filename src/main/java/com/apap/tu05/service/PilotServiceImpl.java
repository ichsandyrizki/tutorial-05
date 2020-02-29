package com.apap.tu05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apap.tu05.model.PilotModel;
import com.apap.tu05.repository.PilotDb;

import java.util.List;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    private PilotDb pilotDb;

    @Override
    public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
        return pilotDb.findByLicenseNumber(licenseNumber);
    }

    @Override
    public void addPilot(PilotModel pilot) {
        pilotDb.save(pilot);
    }

    @Override
    public void removePilot(Long id) {
        pilotDb.removeById(id);
    }

    @Override
    public PilotModel updatePilot(PilotModel pilot) {
        PilotModel targetPilot = pilotDb.findByLicenseNumber(pilot.getLicenseNumber());

        targetPilot.setFlyHour(pilot.getFlyHour());
        targetPilot.setName(pilot.getName());
        pilotDb.save(targetPilot);

        return targetPilot;
    }

    @Override
    public List<PilotModel> findAllPilot() {
        return pilotDb.findAll();
    }
}
