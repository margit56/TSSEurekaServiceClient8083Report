/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tss.services;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import com.tss.to.ProductTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

/**
 *
 * @author mariusz
 * https://www.baeldung.com/spring-rest-template-list  - lista przez RestTemplate
 */
@Service
public class ProductService {
    
   @Autowired
   RestTemplate restTemplate;
   
    @Autowired
    private LoadBalancerClient loadBalancerClient;   
    
   
    java.util.List<ProductTO> productList = new java.util.ArrayList(); 
    //lista caschuje produkty więc przy update może wziąć stąd starą wersję do uzupełnienia
    //formularza zeby nie musiał dociągać danych z mikroserwisu


    @PostConstruct
    private void init() {
        
      
      
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", java.util.Locale.ENGLISH);
      java.util.Date date = new java.util.Date();
      try
      {
        date = formatter.parse("2023-01-01");
      }
      catch(java.text.ParseException ex)
      {
          
      }

//      productList
//              .add(new ProductTO(1l,"Komputer 1", new java.math.BigDecimal(2312), date,"opis......."));
//      productList
//              .add(new ProductTO(2l,"Komputer 2", new java.math.BigDecimal(12312), date,"opis......."));
//      productList
//              .add(new ProductTO(3l,"Komputer 3", new java.math.BigDecimal(22312), date,"opis......."));
//      productList
//              .add(new ProductTO(4l,"Komputer 4", new java.math.BigDecimal(32312), date,"opis......."));
//      productList
//              .add(new ProductTO(5l,"Komputer 5", new java.math.BigDecimal(42312), date,"opis......."));
//                      
    
    }
    
    public java.util.List<ProductTO> productList()
    {
        //wczytanie listy produktów
        loadProductListFromMicroservice();
        return productList;
    }
    
    
    
    private void loadProductListFromMicroservice()
    {
        
        //https://www.baeldung.com/spring-rest-template-list
        
//        ResponseEntity<String> responseString =
//        restTemplate.getForEntity(
//        "http://eurekaServiceClientProfile/products/",
//        String.class);  
//        
//        String productsString = responseString.getBody();
        
        
        ResponseEntity<ProductTO[]> response =
        restTemplate.getForEntity(
        "http://eurekaServiceClientProfile/products/",
        ProductTO[].class);
        
        ProductTO[] products = response.getBody();
        //productList.clear();
        productList = java.util.Arrays.asList(products);
    }
    
    
       
//     String identyfikator="";
//
//     ResponseEntity<String> restExchange =
//     restTemplate.exchange(
//     "http://eurekaServiceClientProfile/products/",
//     HttpMethod.GET, null,
//     String.class, identyfikator);
//     model.addAttribute("attribute", restExchange.getBody()); 
//     return "index.html";    
    
}
