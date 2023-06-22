package com.ejercicios.sesion10_11_12;

import com.ejercicios.sesion10_11_12.entities.Laptop;
import com.ejercicios.sesion10_11_12.repository.LaptopRepository;
import java.time.LocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Sesion10_11_12Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Sesion10_11_12Application.class, args);
                LaptopRepository repository = context.getBean(LaptopRepository.class);
                
                Laptop Laptop1 = new Laptop(null, "HP", "i5", 4, 500, true, 752.99, LocalDate.of(2023, 05, 25));
                Laptop Laptop2 = new Laptop(null, "Lenovo", "i3", 8, 1000, false, 649.00, LocalDate.of(2023, 06, 28));
                Laptop Laptop3 = new Laptop(null, "Asus", "i7", 8, 1000, true, 889.00, LocalDate.of(2023, 03, 01));
                
                System.out.println("Numeros de Laptops en base de datos: " + repository.findAll().size());
                repository.save(Laptop1);
                repository.save(Laptop2);
                repository.save(Laptop3);

	}

}
