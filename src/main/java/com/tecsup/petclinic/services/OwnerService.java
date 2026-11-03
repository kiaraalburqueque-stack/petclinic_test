package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import java.util.List;

public interface OwnerService {
    Owner create(Owner owner);
    Owner update(Owner owner);
    void delete(Integer id) throws OwnerNotFoundException;
    Owner findById(Integer id) throws OwnerNotFoundException;
    List<Owner> findByLastName(String lastName);
    List<Owner> findByCity(String city);
}
