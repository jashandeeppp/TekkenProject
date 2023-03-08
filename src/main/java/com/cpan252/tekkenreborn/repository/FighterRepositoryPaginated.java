package com.cpan252.tekkenreborn.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cpan252.tekkenreborn.model.Fighter;
import com.cpan252.tekkenreborn.model.Fighter.Anime;

  /*
   * We esentially implement paging and sorting repository. 
   * For ex. We have 30 fighters
   */
  // This interface will extend PagingAndSortingRepository, which will allow us to retrieve fighters in pages.
  // We need to implement PageRequest( we provide how many elements we want to query and show it on the screen so we don't do filtering)
public interface FighterRepositoryPaginated extends PagingAndSortingRepository<Fighter,Long> {
    
}
 