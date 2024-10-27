package com.buildbot.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buildbot.tech.entity.ContactsEntity;

public interface ContactRepository extends JpaRepository<ContactsEntity, Integer>{

}
