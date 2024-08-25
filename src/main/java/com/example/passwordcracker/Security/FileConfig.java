package com.example.passwordcracker.Security;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class FileConfig {
    public void fileWriting(String passwordForm){
        String outputText = "src/main/java/com/example/passwordcracker/Files/Hashedtext.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputText, true))) {
            String password;
                if (passwordForm != null && !passwordForm.isEmpty()) {
                    String hashedPasswordSHA256 = sha256(passwordForm);
                    String hashedPasswordMD5 = MD5(passwordForm);
                    writer.write(passwordForm + " : SHA256 = " + hashedPasswordSHA256 + " MD5 = " + hashedPasswordMD5);
                    writer.newLine();
                    System.out.println("Skriver till fil: " + passwordForm);
                }
            System.out.println("Färdig med att skriva till fil.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileReading(){
        String path = "src/main/java/com/example/passwordcracker/Files/Hashedtext.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public static String sha256(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for(int i =0 ; i < encodedhash.length ; i++){
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if(hex.length() == 1)
                {
                    hexString.append('0');

                }
                hexString.append(hex);
            }
            return hexString.toString();

        }
        catch (Exception e){ throw new RuntimeException(e);

        }

    }

    public static String MD5(String password){

        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for(int i = 0 ; i < encodedhash.length ; i++){
                hexString.append(Integer.toHexString(encodedhash[i] & 0xFF | 0x100).substring(1,3));
            }
            return hexString.toString();
        }
        catch(NoSuchAlgorithmException e){ throw new RuntimeException(e);
        }

    }
}
