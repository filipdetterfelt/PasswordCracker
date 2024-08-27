package com.example.passwordcracker.Security;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class FileConfig {

    public String cPw = "src/main/java/com/example/passwordcracker/Files/commonPasswords.txt";
    public String hPw = "src/main/java/com/example/passwordcracker/Files/hashed.txt";

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

    public void crackPassword(){
       // File originalFile = new File("hashed.txt");
        String md5Password = "";
        String sha256Password = "";

          //  if(!originalFile.exists()) {
            //    File newFile = new File(hPw);

                try (BufferedReader reader = new BufferedReader(new FileReader(cPw));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(hPw, false))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        md5Password = MD5(line);
                        sha256Password = sha256(line);
                        writer.write(line + " : " + md5Password + " : " + sha256Password);
                        writer.newLine();
                        writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

           // }
    }

    public String deHashMD5(String actualHash){
        try(BufferedReader reader = new BufferedReader(new FileReader(hPw))){
            String raw;
            while ((raw = reader.readLine()) != null){
                String[] parts = raw.split(" : ");


                if(parts.length == 3 && parts[1].equals(actualHash)){
                    return "Password is: " + parts[0];
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "Can not find any match";
    }

    public String deHashSHA256(String actualHash){
        try(BufferedReader reader = new BufferedReader(new FileReader(hPw))){
            String raw;
            while ((raw = reader.readLine()) != null){
                String[] parts = raw.split(" : ");

                if(parts.length == 3 && parts[2].equals(actualHash)){
                    return "Password is: " + parts[0];
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "Can not find any match";
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
