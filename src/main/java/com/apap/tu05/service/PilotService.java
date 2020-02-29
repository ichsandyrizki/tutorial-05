package com.apap.tu05.service;

import com.apap.tu05.model.PilotModel;
import java.util.List;

public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
    void addPilot(PilotModel pilot);
    void removePilot(Long id);
    PilotModel updatePilot(PilotModel pilot);
    List<PilotModel> findAllPilot();
}
