package org.example.PTSantiago;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"org.example.PTSantiago.Repositories"})
@EnableTransactionManagement
@EnableEncryptableProperties
@EntityScan(basePackages={"org.example.PTSantiago.Entities"})
public class PTSantiagoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PTSantiagoApplication.class, args);
    }

}
