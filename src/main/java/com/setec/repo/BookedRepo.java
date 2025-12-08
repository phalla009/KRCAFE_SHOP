package com.setec.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setec.entities.Booked;

public interface BookedRepo extends JpaRepository<Booked, Integer> {

}
