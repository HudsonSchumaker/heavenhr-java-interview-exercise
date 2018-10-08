package br.com.schumaker.heavenhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@SpringBootApplication
@ComponentScan("br.com.schumaker.heavenhr") 
public class HeavenhrApiApplication {
	public static void main(String... args) {
		SpringApplication.run(HeavenhrApiApplication.class, args);
	}
}