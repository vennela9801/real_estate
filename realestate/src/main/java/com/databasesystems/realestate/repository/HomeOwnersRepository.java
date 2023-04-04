package com.databasesystems.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.databasesystems.realestate.entity.HomeOwnerEntity;

public interface HomeOwnersRepository extends JpaRepository<HomeOwnerEntity, Integer>{

}
