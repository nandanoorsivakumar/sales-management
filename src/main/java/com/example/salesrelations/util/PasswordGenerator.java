package com.example.salesrelations.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



        System.out.println("admin123 Passord is  "+encoder.encode("admin123"));
        System.out.println("user123 password is "+encoder.encode("siva123"));



       /* admin123 Passord is  $2a$10$QXuf5gsVYIohS5yjLu2eFuFLWoHUTJM/GcVziId8Qs8qYSo0G4wNO
        user123 password is $2a$10$yQsBxqT62bZpXo.qO2N.BOi7x4LsGgniGOJ75YFfpas8uYDZdXw1i

        newuser -  newuser123
*/



    }
}
