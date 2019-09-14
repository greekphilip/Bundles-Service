/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.bundleservice;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, Integer>{
    
    public List<Bundle> findByNameContaining(String name);
    
    public List<Bundle> findAllByOrderByPriceAsc();
    
    public List<Bundle> findAllByOrderByPriceDesc();
    
    public List<Bundle> findByNameContainingOrderByPriceAsc(String name);
    
    public List<Bundle> findByNameContainingOrderByPriceDesc(String name);
}
