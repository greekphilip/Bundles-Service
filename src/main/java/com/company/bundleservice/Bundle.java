/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.bundleservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ApiModel
public class Bundle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     
    private Integer code;
    
    @ApiModelProperty(example="Free4All")
    private String name;
    @ApiModelProperty(example="0.0")
    private double price;
    
    @ApiModelProperty(example="2019-10-25")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expiration;
    
    @ApiModelProperty(example="2019-10-20")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date availability;
    
    public Bundle(String name, double price, Date expiration, Date availability) {
        this.name = name;
        this.price = price;
        this.expiration = expiration;
        this.availability = availability;
    }
    
    public Bundle(){
        
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setAvailability(Date availability) {
        this.availability = availability;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Date getExpiration() {
        return expiration;
    }

    public Date getAvailability() {
        return availability;
    }
    
    
    @Override
    public String toString() {
        return "Bundle{" +
                "code='" + code + '\'' +
                ",name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", expiration='" + expiration + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
    
}
