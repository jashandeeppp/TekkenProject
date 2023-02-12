package com.cpan252.tekkenreborn.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data   
@Builder
/*In this class we design the basic model of our fighter
 * Here we define which attributes we want like name, damagePerHit etc.
*/
@Table
/*
 * @Table, @Id annotations are from Spring Data Package and are useful.
 * By using the @Table an we are telling Spring data to map the table name to actual table
 * We are telling we have a dataabase with a Fighter Table and please consider this class as a model for figher table. 
 */

public class Fighter {

    @Id // We mark id field within id annotation and it is very useful for us because database table needs a primary key which is id.
    // Here we added annotations like @NotBlank, @Max, @Min, @DecimalMin, @DecimalMax because we want to do validation so that user enters the correct value only.
    private Long id; // it is imported for the database.
    @NotBlank
    private String name;
    @Max(100)
    private int damagePerHit;
    @Min(1000)  
    private int health;
    @DecimalMin(value = "0.1", inclusive = true)
    @DecimalMax(value = "10.0", inclusive= true)
    private BigDecimal resistance;
    /*This one here, it is used to provide options to select from a option
    * As we want fighters from different animes so we have defined enum so that we can choose characters from multiple animes.
     */ 
    private Anime animeFrom;

    // private final Date createdAt = new Date();
    //We need to create a time stamp but if we use builder and we set thing by default and not provided when we actually the constructing the object then it just ignores LocalDateTime field. IF we provide @Builder.Default it fills the set by default in the builder.  
    
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Anime{   //defined enum
        NARUTO("Naruto"), BLEACH("Bleach"), ONE_PIECE("One Piece"), TEKKEN("Tekken");    // Defined different animes

        private String title;

        // its like a constructor
        private Anime(String title){
            this.title = title;
        }

        // its a getter which returns the value.
        public String getTitle(){
            return title;
        }
    }
    
}
