package com.example;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class GForm {

    public static void main(String[] args) {
        SpringApplication.run(GForm.class, args);
    }

    @Controller
    public class Inner {

        @GetMapping("/process_form")
        public String display() {
            return "index";
        }

        @GetMapping("/admin")
        public String admin() {
            return "admin";
        }

        @PostMapping("/admin")
        public String adminLogin(@RequestParam("username") String name, @RequestParam("password") String pass) {
            if (name.equals("thachu") && pass.equals("123")) {
                return "form";
            }
            return "username or password doesn't exist";
        }

        public String getMethodName(@RequestParam String param) {
            return new String();
        }

        @PostMapping("/submit_form")
        public ResponseEntity<String> show(@RequestParam("name") String n1, @RequestParam("gender") String n2,
                @RequestParam("rollno") String n3,
                @RequestParam("email") String n4, @RequestParam("mobile") String n5, @RequestParam("cgpa") String n6,
                @RequestParam("domain") String n7) {
            String path = "form.csv";
            try {
                FileWriter write = new FileWriter(path, true);

                write.append(n1).append(",").append(n2).append(",").append(n3).append(",").append(n4).append(",")
                        .append(n5).append(",").append(n6).append(",").append(n7).append("\n");

                write.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return ResponseEntity.status(500).body("Error occurred while writing to file.");
            }
            return ResponseEntity.ok("Your data has been Sumbited ");
        }

    }

}
