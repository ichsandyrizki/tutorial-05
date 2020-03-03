package com.apap.tu05.controller;
import com.apap.tu05.model.FlightModel;
import com.apap.tu05.model.PilotModel;
import com.apap.tu05.service.FlightService;
import com.apap.tu05.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    /*@RequestMapping(value = "/flight/delete/{flightId}/{pilotLicenseNumber}", method = RequestMethod.GET)
    private String delete(@PathVariable(value = "flightId") Long flightId, @PathVariable(value="pilotLicenseNumber") String licenseNumber ,Model model){
        flightService.deleteFlight(flightId);
        return "redirect:/pilot/view?licenseNumber="+ licenseNumber;
    }*/
    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        FlightModel flight = new FlightModel();
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        flight.setPilot(pilot);
        model.addAttribute("flight", flight);
        model.addAttribute("navTitle", "Add Flight");
        return "addFlight";
    }
    @RequestMapping(value = "/flight/add", method = RequestMethod.POST)
    private String addFlightSubmit(@ModelAttribute FlightModel flight, Model model){
        flightService.addFlight(flight);
        model.addAttribute("message", "Flight Berhasil Dimasukan!");
        return "redirect:/pilot/view?licenseNumber="+flight.getPilot().getLicenseNumber();
    }

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/flight/view/{flightNumber}")
    public String view(@PathVariable(value = "flightNumber") String flightNumber, Model model){
        FlightModel flight = flightService.findByFlightNumber(flightNumber);
        model.addAttribute("flight", flight);
        model.addAttribute("navTitle", "Flight Detail");
        return "viewFlight";

    }


    @RequestMapping(value = "/flight/update/{flightId}", method = RequestMethod.GET)
    private String update(@PathVariable(value = "flightId") Long flightId, Model model){
        FlightModel flight = flightService.findById(flightId);
        model.addAttribute("flight", flight);
        model.addAttribute("navTitle", "Update Flight");
        return "updateFlight";
    }

    @RequestMapping(value = "flight/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute PilotModel pilot, Model model){
        for(FlightModel flight : pilot.getPilotFlight()){
            flightService.deleteFlight(flight.getId());
        }
        return "redirect:/pilot/view?licenseNumber="+ pilot.getLicenseNumber();
    }

    @RequestMapping(value = "flight/update", method = RequestMethod.POST)
    private String updateFlightSubmit(@ModelAttribute FlightModel flightModel, Model model){
        flightService.updateFlight(flightModel);
        return "redirect:/pilot/view?licenseNumber="+flightModel.getPilot().getLicenseNumber();
    }
}


