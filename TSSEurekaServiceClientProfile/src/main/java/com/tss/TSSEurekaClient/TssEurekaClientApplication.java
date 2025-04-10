package com.tss.TSSEurekaClient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.tss.entities.Product;
import com.tss.repositories.ProductRepository;

@SpringBootApplication
@EnableEurekaClient 
//annotation is not available in the following spring cloud version
//<spring-cloud.version>2022.0.0-RC2</spring-cloud.version>
//Zmieniona wersja spring-cloud.version na 
//<spring-cloud.version>2021.0.5</spring-cloud.version>
//bo w 2022.0.0-RC2 nie ma @EnableEurekaClient
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.tss"})
@EnableJpaRepositories(basePackages="com.tss.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.tss.entities")
public class TssEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TssEurekaClientApplication.class, args);
	}
        
       
        @Value("${server.port}")
        String port;       
        
        @Value("${build.version}")
        String buildVersion;       

        @Value("${build.timestamp}")
        String buildTimestamp;         
        
        @RequestMapping(value = "/")
        public String home() {

            String htmlContent="<div>Eureka Service Client Products  - Dwa profile - Dwa mikroserwisy - port: " + port+"</div>";
            htmlContent=htmlContent.concat("<div> <h5> Wersja: "+ buildTimestamp  +"  </h5> </div>");
           return htmlContent;
        }    
        
  @Bean
  public CommandLineRunner databseInitialize(ProductRepository productRepository) {
    return (args) -> {
      // zapis danych produktów w bazie
//        Product product = new Product();
//        product.setName("Komputer");
//        product.setPrice(new java.math.BigDecimal(10200));
//        product.setUpdated(new java.util.Date("2022-01-01"));
//        productRepository.save(product);      
    };       
 }        

//"D:\Program Files\jdk-17\bin\java"  -jar -Dspring.profiles.active=8081 TSSEurekaServiceClientProfile-0.0.1-SNAPSHOT.jar        

}
