package com.CSMS.CSMS.services.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;

import com.CSMS.CSMS.ConsumeAPI.ApiService;
import com.CSMS.CSMS.Repository.CustomerRepo;
import com.CSMS.CSMS.exception.CustomException;
import com.CSMS.CSMS.exception.InvalidRequest;
import com.CSMS.CSMS.models.Customer;
import com.CSMS.CSMS.request.SignupRequest;
import com.CSMS.CSMS.response.AuthResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {
    @Autowired
    private CustomerRepo userRepository;

    @Autowired
    private ApiService apiService;

    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthResponse loginUser(String email, String password) throws MessagingException, IOException, Exception {
        this.logger.info(email + " LOGIN REQUEST");
        this.validateData(email, password);

        // find if user is present in database to authenticate
        Optional<Customer> userContainer = this.userRepository.findByCustomerEmail(email);
        if (userContainer.isEmpty()) {
            throw new CustomException("User with email " + email + " does not exist");
        }

        Customer user = userContainer.get();
        // user exists in database, check if login password is correct
        String hashedPassword = user.getPassword();
        if (!BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified) {
            // invalid credentials for user
            this.logger.warn("FAILED LOGIN ATTEMPT FOR EMAIL " + email);
            throw new CustomException("Invalid login credentials provided");
        }
        // user has entered correct details, generate token and send response
        String token = this.generateJWTToken(user.getCustomer_email());
        // if(!token.equals(jwtToken)){
        //     throw new Exception("UnAuthorized");
        // }
        return new AuthResponse(user.getRole_id(),user.getCustomer_email(), user.getCustomer_firstName(), user.getCustomer_phone(), token);

    }

    public AuthResponse signupUser(SignupRequest request) throws MessagingException, IOException {
        this.logger.info(request.customerEmail + " SIGNUP REQUEST");
        this.validateSignupData(request.roleId,request.customerEmail, request.password, request.customerFirstName, request.customerPhone, request.dobCustomer, request.customerGender, request.customerNationality);
        // details are valid till now

        if (this.userRepository.countOfUsersByEmailOrMobile(request.customerEmail, request.customerPhone) != 0) {
            // some other user has taken email or mobile, hence unique constraint will be violated
            throw new CustomException("Email or mobile is not unique");
        }
        String ocppTag = RandomString.getAlphaNumericString(10);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date input = calendar.getTime();
        LocalDate da = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date expDate = calendar.getTime();
        // sign up user
        String hashedPassword = BCrypt.withDefaults().hashToString(10, request.password.toCharArray());
        Customer savedUser = this.userRepository.save(new Customer(request.roleId, request.customerEmail, request.customerFirstName, request.customerLastName, request.dobCustomer, request.customerGender, request.customerNationality, request.customerPhone, ocppTag, ocppTag, expDate, hashedPassword));
        String token = generateJWTToken(request.customerEmail);
        // String expiryTime=booking.getDate()+"T"+booking.getEnd_time()+":00";
        HashMap<String,String> store = new HashMap<>();
        store.put("idTag",ocppTag);
        store.put("parentIdTag",ocppTag);
        store.put("expiryDate",String.valueOf(da));
        store.put("note", "Added");
        store.put("maxActiveTransactionCount","99");
        String getResult= apiService.addOcppTag(store);
        // return the response
        return new AuthResponse(savedUser.getRole_id(),savedUser.getCustomer_email(), savedUser.getCustomer_firstName(), savedUser.getCustomer_phone(), token);

    }

    // helper methods

    private void validateSignupData(Integer roleId, String email, String password, String name, String mobile, Date dob , String gender, String nationality) {
        this.validateData(email, password);
        ArrayList<String> invalidData = new ArrayList<String>();
//        if(roleId != 1 || roleId != 2 || roleId != 3)
//            invalidData.add("roleId is wrong");
        if (name == null || name.length() < 3) {
            invalidData.add("Name should be atleast 3 characters");
        }
        if (mobile == null) {
            invalidData.add("Invalid mobile number");
        } else {
            // check if mobile number is valid
            try {
                Pattern phoneNumberPattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");
                Matcher phoneNumberMatcher = phoneNumberPattern.matcher(mobile);
                if (!phoneNumberMatcher.find() && phoneNumberMatcher.group().equals(mobile)) {
                    // invalid mobile number
                    throw new Exception("No match found");
                }
            } catch (Exception e) {
                invalidData.add("Invalid mobile number");
            }
        }

        if(dob == null){
            invalidData.add("Dob can not be null");
        }
        if(!(gender.equals("Male") || gender.equals("Female") || gender.equals("Other"))){
            invalidData.add("Gender is invalid");
        }

        if (invalidData.size() > 0) {
            StringBuilder error = new StringBuilder();
            for (var message : invalidData) {
                error.append(message).append(", ");
            }
            throw new InvalidRequest(error.substring(0, error.length() - 2)); // remove trailing comma
        }
    }

    private void validateData(String email, String password) {
        ArrayList<String> invalidData = new ArrayList<String>();
        if (email == null || !EmailValidator.getInstance().isValid(email)) {
            invalidData.add("Email is invalid");
        }
        if (password == null || password.length() < 6) {
            invalidData.add("Length of Password should be atleast 6 characters");
        }
        if (invalidData.size() > 0) {
            StringBuilder error = new StringBuilder();
            for (var message : invalidData) {
                error.append(message).append(", ");
            }
            throw new InvalidRequest(error.substring(0, error.length() - 2)); // remove trailing comma
        }
    }

    private String generateJWTToken(String email) {
        Algorithm algorithmHS = Algorithm.HMAC256(this.getJwtSecret());
        return JWT.create()
                .withIssuer(this.getIssuer())
                .withClaim("email", email)
                .sign(algorithmHS);
    }

    private String getJwtSecret() {
        // fallback to random string if jwt_secret is not set
        String jwtSecret = System.getenv("JWT_SECRET");
        if (jwtSecret == null || jwtSecret.length() == 0) {
            jwtSecret = "askdjasjdlaskjdlasjdlasjdklasjdaslkfdajhsfgajsgdhasd";
        }
        return jwtSecret;
    }

    private String getIssuer() {
        // fallback issuer if Issuer is not set
        String issuer = System.getenv("ISSUER");
        if (issuer == null || issuer.length() == 0) {
            issuer = "CSMS";
        }
        return issuer;
    }



}