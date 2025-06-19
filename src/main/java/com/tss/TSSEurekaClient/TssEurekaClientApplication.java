package com.tss.TSSEurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;


import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableEurekaClient 
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.tss"})
@RestController
public class TssEurekaClientApplication {
    
    @Autowired
    private Environment env;
    
    @Autowired
    private ServerProperties serverProperties;    
    
    @Value("${build.version}")
    String buildVersion;       

    @Value("${build.timestamp}")
    String buildTimestamp;  
    
	public static void main(String[] args) {
		SpringApplication.run(TssEurekaClientApplication.class, args);
	}
        
        @RequestMapping(value = "/")
        public String home() {
//           String hostname = serverProperties.getAddress().toString();
//           String hostnameIP = env.getProperty("server.address");
           String port = env.getProperty("server.port");
           String htmlContent ="<div> Eureka Service Client Report port: " + port + "</div> ";
           htmlContent = htmlContent+"<div><a href=\"/reports/userreportpdf\">productReport</a>  </div>";
           htmlContent=htmlContent+"<div> <h5> Wersja: "+ buildTimestamp  +"  </h5> </div>";
           return htmlContent;
        }  
        
        @LoadBalanced
        @Bean
        public RestTemplate getRestTemplate(){
        return new RestTemplate();
        }
        
  
//       
//
//    @Bean
//    public HttpMessageConverter customConverters() {
//        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
//        return arrayHttpMessageConverter;
//    }        

}
