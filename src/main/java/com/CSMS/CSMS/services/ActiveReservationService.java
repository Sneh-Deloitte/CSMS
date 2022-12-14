package com.CSMS.CSMS.services;

import com.CSMS.CSMS.models.ActiveReservation;

import java.util.List;

public interface ActiveReservationService {

    public List<ActiveReservation> getAllActiveReservation();

    public ActiveReservation addActiveReservation(ActiveReservation activeReservation);

    public String deleteActiveReservation(Long id);

    public boolean getActiveStatus(ActiveReservation activeReservation);
}
