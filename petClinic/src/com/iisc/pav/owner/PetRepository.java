/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iisc.pav.owner;

import com.iisc.pav.others.RepoException;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
//import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for <code>Pet</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data. See:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public class PetRepository  {
	private static List<Pet> table = new ArrayList<>();
	static {
		Owner o = new Owner(10,"ten","man","ten place","ten city","10");
		new OwnerRepository().save(o);
		Pet p = new Pet(4, LocalDate.now(),new PetType("bird"),o);
		table.add(p);
	}

	/**
	 * Retrieve all {@link PetType}s from the data store.
	 * @return a Collection of {@link PetType}s.
	 */
//	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
//	@Transactional(readOnly = true)
	List<PetType> findPetTypes() {
		return new ArrayList(PetType.petTypes);
	}

	/**
	 * Retrieve a {@link Pet} from the data store by id.
	 * @param id the id to search for
	 * @return the {@link Pet} if found
	 */
//	@Transactional(readOnly = true)
	Pet findById(Integer id) {
		for(Pet pet:table) {
			if(pet.getId() == id) {
				return pet;
			}
		}
		throw new RepoException("Pet not found");
	}

	/**
	 * Save a {@link Pet} to the data store, either inserting or updating it.
	 * @param pet the {@link Pet} to save
	 */
	void save(Pet pet) {
		table.add(pet);
	}

}
