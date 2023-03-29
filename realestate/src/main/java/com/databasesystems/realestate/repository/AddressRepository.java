package com.databasesystems.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.databasesystems.realestate.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{

}
