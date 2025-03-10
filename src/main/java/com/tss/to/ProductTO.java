/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tss.to;


import java.math.BigDecimal;
import java.util.Date;

public class ProductTO {


    private Long id;
    

    private String name;
    

    protected BigDecimal price;  
    

    private Date updated;      
    

    private String description; 
    
    public ProductTO()
    {
    //musi być pusty konstruktor zadeklarowany dla RestTemplate
    }
    
    public ProductTO(Long id, String name, BigDecimal price, Date updated, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.updated = updated;
        this.description = description;
    }
    
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductTO)) {
            return false;
        }
        ProductTO other = (ProductTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tss.entities.Product[ id=" + id + " ]";
    }
    
}
