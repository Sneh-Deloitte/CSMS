package com.CSMS.CSMS.ConsumeAPI;

import com.CSMS.CSMS.ConsumeAPI.dto.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class ApiService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${API_URL}")
    private String API_URL;

    public void addCharger(ChargePointForm chargePointForm) {
        String url = API_URL+"/chargePointAddition";
        ResponseEntity<String> result = restTemplateBuilder.build().postForEntity(url, chargePointForm, String.class);
        System.out.println(result);
    }


    public String addReservation(HashMap<String,String> reserveNowParams){
        String url = API_URL+"/reserveNow";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        JSONObject jsonObject = null;

        ResponseEntity<String> result = restTemplateBuilder.build().postForEntity(url, reserveNowParams, String.class);
        System.out.println(result);
        return result.getBody();
    }


    public List<OcppJsonStatus> getActiveChargers(){
        String url = API_URL+"/connectedChargePoint";
        ResponseEntity<OcppJsonStatus[]> response = restTemplateBuilder.build().getForEntity(url,  OcppJsonStatus[].class);

        OcppJsonStatus[] res =response.getBody();

        for (int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }

        System.out.println(response.getBody());

        return List.of(response.getBody());
    }




}
