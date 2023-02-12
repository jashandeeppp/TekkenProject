// package com.cpan252.tekkenreborn.repository.impl;

// import java.util.Optional;

// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.support.GeneratedKeyHolder;
// import org.springframework.stereotype.Repository;

// import com.cpan252.tekkenreborn.model.Fighter;
// import com.cpan252.tekkenreborn.repository.FighterRepository;



// //*** There's an acronym called CRUD that stands for Create, Read, Update, Delete
// //*** This is a repository that implements the CRUD operations for fighter table.
// @Repository     // IF we don't annotate with any spring component annotation, this should be plain java object
//                 // but if we annotate with class annotation like component, repository, controller,or any spring bean annotation
//                 // we tell spring this is spring bean and this is not a regular class.
// public class JdbcFighterRepository implements FighterRepository{
//     // here we could use @Autowired annotation here but it is not recommmended
//     // this approach is more testable and more recommended than using @Autowired.
//     private JdbcTemplate jdbcTemplate;

//     //****JdbcTemplate is a bean provided by Spring
//     /*
//      * We used constructor like this and spring understands that on the creation of the @Repository we have to inject the jdbcTemplate through constructor in JdbcTemplate bean 
//      * so we will able to use it going forward.
//      * In simple words,(above explaination) When spring creates the instance of @Repository it will inject jdbcTemplate so that we can use it in our methods. (Working explanation: When we start the project Spring has to create the instance of the bean by itself and we dont need to create it and spring create it and manages it.)
//      * When we use JdbcFighterRepsitory we will be able to use jdbcTemplate because
//      * It was instantiate with the help of constructor. 
//      */
//     // In the constructor we have provided JdbcTemplate essentially the oject that helps you to interact with jdbc without setting any complex feature or any complex problems. It is more of a simple approach.
//     public JdbcFighterRepository(JdbcTemplate jdbcTemplate){
//         this.jdbcTemplate = jdbcTemplate;
//     }

//     @Override
//     public Iterable<Fighter> findAll() {
//         // To find all fighters we have to use jdbcTemplate.
//         /*
//          * We are returning jdbcTemplate.query and we provide two parameters.
//          * So, one parameter is sql query and the second parameter is row mapper for fighter which we defined in other file named FighterRowMapper() so we create object of it over here.
//         /******
//          * 1. WE query the fighter table for all the fighters
//          * 2. We iterate each row returned from the table.
//          * 3. We create a fighter object from each row(map row to Fighter object)
//          * Optional: we can provide RowMapper that will be responsible for mapping the row to Fighter object.
//          */
//         return jdbcTemplate.query("SELECT * FROM fighter",new FighterRowMapper());
//     }

//     @Override
//     public Optional<Fighter> findById(Long id) {
//         // We used queryForObject, we select from figther table, we provide the rowMapper to map the row to actual fighter object and we provide id.
//         Fighter fighter = jdbcTemplate.queryForObject("SELECT * FROM fighter WHERE id = ?", new FighterRowMapper(), id);
//         // optional is used when we don't know that the figher is present or not. 
//         return Optional.ofNullable(fighter);
//     }

//     @Override
//     // WE change the data in the fighter table with the save because we want to save the changes which we added.
//     public void save(Fighter fighter) {
//         var insertFighter = "INSERT INTO fighter (name, damage_per_hit, health, resistance, anime_from, created_at) VALUES (?,?,?,?,?,?)";
//         // when we provide keyHolder the id for the fighter is autogenerated as we don't want to generate id by ourselves.
//         var keyHolder = new GeneratedKeyHolder();
//         jdbcTemplate.update(connection -> {
//             var ps = connection.prepareStatement(insertFighter);
//             ps.setString(1, fighter.getName());
//             ps.setInt(2, fighter.getDamagePerHit());
//             ps.setInt(3, fighter.getHealth());
//             ps.setBigDecimal(4, fighter.getResistance());
//             ps.setString(5, fighter.getAnimeFrom().name());
//             ps.setString(6, fighter.getCreatedAt().toString());
//             return ps;
//         }, keyHolder);
//     }
    
// }
