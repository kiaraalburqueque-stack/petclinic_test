package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Integer id) throws OwnerNotFoundException {
        ownerRepository.delete(findById(id));
    }

    @Override
    public Owner findById(Integer id) throws OwnerNotFoundException {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException("Owner not found: " + id));
    }
}
