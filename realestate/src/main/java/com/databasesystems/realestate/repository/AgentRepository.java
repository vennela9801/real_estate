package com.databasesystems.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.databasesystems.realestate.entity.Agent;

@SuppressWarnings("rawtypes")
public interface AgentRepository extends JpaRepository<Agent, Integer>{

}
