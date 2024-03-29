package com.multinationals.visa.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multinationals.visa.api.model.Visa;

public interface VisaRepository extends JpaRepository<Visa, Long>{
    // List<Visa>  
}
