package com.example.phone2;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByLastNameStartsWithIgnoreCase(String lastName);

}
