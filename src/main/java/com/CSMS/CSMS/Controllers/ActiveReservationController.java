package com.CSMS.CSMS.Controllers;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.CSMS.CSMS.models.ActiveReservation;
import com.CSMS.CSMS.services.ActiveReservationService;

@CrossOrigin(origins = "*")
@RestController
public class ActiveReservationController {

    @Autowired
    private ActiveReservationService activeReservationService;

    @GetMapping("/infinte")
    public void infinity(){

        Timer timer = new Timer ();
        timer.schedule (hourlyTask, 0l, 20000);
        System.out.println("new");
    }


    TimerTask hourlyTask = new TimerTask () {
        @Override
        public void run () {
           System.out.println("Running in every 20 seceonds");

            List<ActiveReservation> listActiveReservation= activeReservationService.getAllActiveReservation();
            for(int i=0;i<listActiveReservation.size(); i++){

                System.out.println(activeReservationService.getActiveStatus(listActiveReservation.get(i)));
            }

        }
    };

    @GetMapping("/deleteActiveReservation/{id}")
    public String deleteActiveReservation(@PathVariable Long id){
        String response = activeReservationService.deleteActiveReservation(id);
        return response;
    }
    
}
