/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.bundleservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BundleController {
    
    @Autowired
    BundleRepository bundleRepository;
    

    //Display all bundles
     @GetMapping("/bundle")
    public List<Bundle> index(){
        return bundleRepository.findAll();
    }
    
    //Create new bundle
    @PostMapping("/bundle")
    public Bundle create(@RequestBody Map<String,String> body) throws ParseException{
            Date expiration;
            try{
                 expiration = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("expiration"));
            }catch(NullPointerException e){
                 expiration=null;
            }
            String name = body.get("name");
            Double price = Double.parseDouble(body.get("price"));
            Date availability = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("availability"));
            
            
            return bundleRepository.save(new Bundle(name,price,expiration,availability));
    }
    
    //Delete exisitng bundle
    @DeleteMapping("/bundle/{code}")
    public boolean delete(@PathVariable String code){
        int bundleCode = Integer.parseInt(code);
        bundleRepository.delete(bundleCode);
        return true;
    }
    
    //Update the availability date of an existing bundle
    @PutMapping("bundle/{code}")
    public Bundle update(@PathVariable String code, @RequestBody Map<String,String> body) throws ParseException{
        int bundleCode=Integer.parseInt(code);
        Bundle bundle = bundleRepository.findOne(bundleCode);
        Date availability = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("availability"));
        bundle.setAvailability(availability);
        return bundleRepository.save(bundle);
    }
    
    //Querying bundles by code
    @GetMapping("bundles/{code}")
    public Bundle findById(@PathVariable String code){
        int bundleCode = Integer.parseInt(code);
        return bundleRepository.findOne(bundleCode);
    }
    
    //Querying bundles by name
    @PostMapping("bundle/name")
    public List<Bundle> searchByName(@RequestBody Map<String,String> body){
        if(body.get("search")==null){
            return bundleRepository.findAll();
        }else{
            return bundleRepository.findByNameContaining(body.get("search"));
        }
        
    }
    
    //Querying bundles by name, price ascending
    @PostMapping("bundle/asc")
    public List<Bundle> searchByNameAsc(@RequestBody Map<String,String> body){
        if(body.get("search")==null){
            return bundleRepository.findAllByOrderByPriceAsc();
        }else{
            return bundleRepository.findByNameContainingOrderByPriceAsc(body.get("search"));
        }
    }
    
    //Querying bundles by name, price descending
    @PostMapping("bundle/desc")
    public List<Bundle> searchByNameDesc(@RequestBody Map<String,String> body){
        if(body.get("search")==null){
            return bundleRepository.findAllByOrderByPriceDesc();
        }else{
            return bundleRepository.findByNameContainingOrderByPriceDesc(body.get("search"));
        }
    }
}
