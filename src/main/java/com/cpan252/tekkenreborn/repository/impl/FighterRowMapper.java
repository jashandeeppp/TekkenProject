package com.cpan252.tekkenreborn.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cpan252.tekkenreborn.model.Fighter;

/*
 * WE create rowMapper seperated to explain the jdbc how we want to map the fighter from fighter table to the database.
 * 
 * we recived result set as rs and number of rows as rowNum
 * And we are plotting the fields from result set.
 */
// **** This is the bridge between the database(row representation) and the fighter object.
// Rowmapper is used to map the number of rows from a table and make it into a object 
public class FighterRowMapper implements RowMapper<Fighter>{

    @Override
    public Fighter mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Fighter.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .damagePerHit(rs.getInt("damage_per_hit"))
            .health(rs.getInt("health"))
            .resistance(rs.getBigDecimal("resistance"))
            .animeFrom(Fighter.Anime.valueOf(rs.getString("anime_from")))
            .build();
    }
    
}
