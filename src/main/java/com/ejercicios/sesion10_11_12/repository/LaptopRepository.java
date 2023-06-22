
package com.ejercicios.sesion10_11_12.repository;

import com.ejercicios.sesion10_11_12.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>{
    
}
