package com.cpan252.tekkenreborn.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   
@Builder
/*In this class we design the basic model of our fighter
 * Here we define which attributes we want like name, damagePerHit etc.
*/
public class Fighter {

    // Here we added annotations like @NotBlank, @Max, @Min, @DecimalMin, @DecimalMax because we want to do validation so that user enters the correct value only.
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
