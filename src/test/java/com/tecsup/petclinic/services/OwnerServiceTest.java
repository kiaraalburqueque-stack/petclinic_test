package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @Test
    void testCreateOwner() {
        // Arrange: el repositorio asigna un ID a un dueño nuevo.
        Owner input = new Owner(null, "Kiara", "Alburqueque", "Av. Lima 123", "Lima", "987654321");
        Owner saved = new Owner(11, "Kiara", "Alburqueque", "Av. Lima 123", "Lima", "987654321");
        when(ownerRepository.save(input)).thenReturn(saved);

        Owner actual = ownerService.create(input);

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(11, actual.getId());
        assertEquals("Kiara", actual.getFirstName());
        assertEquals("Alburqueque", actual.getLastName());
        assertEquals("Av. Lima 123", actual.getAddress());
        assertEquals("Lima", actual.getCity());
        assertEquals("987654321", actual.getTelephone());
        verify(ownerRepository).save(input);
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void testUpdateOwner() {
        // Un dueño existente cambia sus datos conservando el identificador.
        Owner input = new Owner(11, "Kiara", "Alburqueque", "Av. Lima 123", "Lima", "987654321");
        input.setFirstName("Ana");
        input.setLastName("Gomez");
        input.setAddress("Calle Sol 456");
        input.setCity("Arequipa");
        input.setTelephone("912345678");
        Owner saved = new Owner(11, "Ana", "Gomez", "Calle Sol 456", "Arequipa", "912345678");
        when(ownerRepository.save(input)).thenReturn(saved);

        Owner actual = ownerService.update(input);

        assertNotNull(actual);
        assertEquals(11, actual.getId());
        assertEquals("Ana", actual.getFirstName());
        assertEquals("Gomez", actual.getLastName());
        assertEquals("Calle Sol 456", actual.getAddress());
        assertEquals("Arequipa", actual.getCity());
        assertEquals("912345678", actual.getTelephone());
        verify(ownerRepository).save(input);
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void testDeleteOwner() throws OwnerNotFoundException {
        Owner existing = new Owner(11, "Kiara", "Alburqueque", "Av. Lima 123", "Lima", "987654321");
        when(ownerRepository.findById(11)).thenReturn(Optional.of(existing));

        ownerService.delete(11);

        org.mockito.InOrder order = inOrder(ownerRepository);
        order.verify(ownerRepository).findById(11);
        order.verify(ownerRepository).delete(existing);
        order.verifyNoMoreInteractions();
    }

    @Test
    void testFindOwnerById_NotFound() {
        when(ownerRepository.findById(999)).thenReturn(Optional.empty());

        OwnerNotFoundException exception = assertThrows(OwnerNotFoundException.class,
                () -> ownerService.findById(999));

        assertEquals("Owner not found: 999", exception.getMessage());
        verify(ownerRepository).findById(999);
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void testDeleteOwner_NotFound() {
        when(ownerRepository.findById(999)).thenReturn(Optional.empty());

        OwnerNotFoundException exception = assertThrows(OwnerNotFoundException.class,
                () -> ownerService.delete(999));

        assertEquals("Owner not found: 999", exception.getMessage());
        verify(ownerRepository).findById(999);
        verify(ownerRepository, never()).delete(any(Owner.class));
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void testFindOwnerById() throws OwnerNotFoundException {
        Owner existing = new Owner(21, "Sebastian", "Espiritu", "Av. Sol 100", "Lima", "912345678");
        when(ownerRepository.findById(21)).thenReturn(Optional.of(existing));

        Owner actual = ownerService.findById(21);

        assertNotNull(actual);
        assertEquals(21, actual.getId());
        assertEquals("Sebastian", actual.getFirstName());
        assertEquals("Espiritu", actual.getLastName());
        assertEquals("Av. Sol 100", actual.getAddress());
        assertEquals("Lima", actual.getCity());
        assertEquals("912345678", actual.getTelephone());
        verify(ownerRepository).findById(21);
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void testFindByLastName() {
        Owner first = new Owner(21, "Sebastian", "Espiritu", "Av. Sol 100", "Lima", "912345678");
        Owner second = new Owner(22, "Ana", "Espiritu", "Calle Luna 200", "Cusco", "923456789");
        when(ownerRepository.findByLastName("Espiritu")).thenReturn(List.of(first, second));

        List<Owner> actual = ownerService.findByLastName("Espiritu");

        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertEquals(List.of(first, second), actual);
        assertEquals(List.of(21, 22), actual.stream().map(Owner::getId).toList());
        assertTrue(actual.stream().allMatch(owner -> "Espiritu".equals(owner.getLastName())));
        verify(ownerRepository).findByLastName("Espiritu");
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void testFindByCity() {
        Owner first = new Owner(21, "Sebastian", "Espiritu", "Av. Sol 100", "Lima", "912345678");
        Owner second = new Owner(23, "Luis", "Perez", "Calle Mar 300", "Lima", "934567890");
        when(ownerRepository.findByCity("Lima")).thenReturn(List.of(first, second));

        List<Owner> actual = ownerService.findByCity("Lima");

        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertEquals(List.of(first, second), actual);
        assertEquals(List.of(21, 23), actual.stream().map(Owner::getId).toList());
        assertTrue(actual.stream().allMatch(owner -> "Lima".equals(owner.getCity())));
        verify(ownerRepository).findByCity("Lima");
        verifyNoMoreInteractions(ownerRepository);
    }

	//@Autowired
   //	private OwnerService ownerService;

	/**
	 * 
	 */
	/*
	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String NAME = "Jaime";
		Owner owner = null;
		
		try {
			
			owner = ownerService.findById(ID);
			
		} catch (OwnertNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("" + owner);

		assertEquals(NAME, owner.getName());

	}
	*/
}
