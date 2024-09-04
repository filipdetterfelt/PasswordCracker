Welcome to my application, PasswordCracker, which I built using Java Spring Boot with the support of several key dependencies. I have integrated Thymeleaf into the project and expanded its scope beyond the initial requirements to further enhance my learning experience.

1. You need to run with java(20)

2. And you need to use a database for the register and the manual login with username and password.
 
3. To generate a file `hashed.txt` run this command in the terminal in intelij
  ```
  ./gradlew bootRun --args='HashPasswordApp'
  ```
or if you want to run it in you terminal in windows write this command in your windows terminal
```
./gradlew.bat bootRun --args='HashPasswordApp'
```
 
4. Create a file named commonPasswords.txt inside the /src/main/java/com/example/passwordcracker/Files directory. This file should contain one password per line.

   
  
   Example commonPasswords.txt:

   ```
   password1
   password2
   password3
   password4
   ```
