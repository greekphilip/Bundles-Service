/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.bundleservice;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BundleController {
    
    @Autowired
    BundleRepository bundleRepository;
    
    
    //Display all bundles
     @ApiOperation(value = "Retrieve all bundles", notes="Retrieves all bundles. Use 'asc' or 'desc' parameter to order by price (optional).")
     @ResponseStatus(HttpStatus.OK)
     @GetMapping("/bundles")
    public List<Bundle> index(@RequestParam(defaultValue="code") String orderby){
        if(orderby.equals("code")){
            return bundleRepository.findAll();
        }else if(orderby.equals("asc")){
            return bundleRepository.findAllByOrderByPriceAsc();
        }else if(orderby.equals("desc")){
            return bundleRepository.findAllByOrderByPriceDesc();
        }
        return null;
    }
    
    //Create new bundle
    @ApiOperation(value = "Create new bundle", notes="Copy and paste the Example Value to the body and create a new bundle. Change the name, price and availability/expiration dates to whatever you want.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/bundles")
    public Bundle create(@RequestBody Map<String,String> body) throws ParseException{
            Date expiration;
            Date availability = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("availability"));
            try{
                 expiration = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("expiration"));
            }catch(NullPointerException e){
                 expiration=null;
            }
            String name = body.get("name");
            Double price = Double.parseDouble(body.get("price"));
            
            return bundleRepository.save(new Bundle(name,price,new java.sql.Date(expiration.getTime()),new java.sql.Date(availability.getTime())));
    }
    
    //Delete exisitng bundle
    @ApiOperation(value = "Delete a bundle", notes="Enter the code of the bundle to be deleted.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/bundles/{code}")
    public boolean delete(@PathVariable String code){
        int bundleCode = Integer.parseInt(code);
        bundleRepository.delete(bundleCode);
        return true;
    }
    
    //Update the availability date of an existing bundle
    @ApiOperation(value = "Update availability date of a bundle", notes="Enter the code of the bundle to be updated and enter the new availability date in JSON format in the body.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("bundles/{code}")
    public Bundle update(@PathVariable String code, @RequestBody Map<String,String> body) throws ParseException{
        int bundleCode=Integer.parseInt(code);
        Bundle bundle = bundleRepository.findOne(bundleCode);
        Date availability = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("availability"));
        bundle.setAvailability(new java.sql.Date(availability.getTime()));
        return bundleRepository.save(bundle);
    }
    
    //Querying bundles by code
    @ApiOperation(value = "Retrieve specific bundle", notes="Enter code of a specific bundle to retrieve it")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("bundles/{code}")
    public Bundle findById(@PathVariable String code){
        int bundleCode = Integer.parseInt(code);
        return bundleRepository.findOne(bundleCode);
    }
    
    @ApiOperation(value = "Search bundles by name", notes="Search bundles using name parameter. Use 'asc' or 'desc' to order by price (optional).")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("bundles/findByName")
    public List<Bundle> findByName( @RequestParam(defaultValue= "") String name, @RequestParam(defaultValue= "code") String orderby){
        if(orderby.equals("code")){
            return bundleRepository.findByNameContaining(name);
        }else if(orderby.equals("asc")){
            return bundleRepository.findByNameContainingOrderByPriceAsc(name);
        }else if(orderby.equals("desc")){
            return bundleRepository.findByNameContainingOrderByPriceDesc(name);
        }
        return null;
    }
    
}
