package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    List<Owner> findByLastName(String lastName);
}
