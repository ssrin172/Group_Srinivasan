package dev.Group_Srinivasan.JavaSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class JavaSpringApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "INSERT INTO user_credentials (username, password) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql,"example_user7","hashed_password7");
        if(result > 0 ){
            System.out.println("Rows inserted");
        }
    }
}
