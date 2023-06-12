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

//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Repository class for <code>Owner</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data. See:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public class OwnerRepository  {
    private static List<Owner> table = new ArrayList<>();

    static {
    	Owner o1 = new Owner();
    	o1.setId(3);
    	o1.setLastName("Smith");
		o1.setFirstName("John");
		o1.setAddress("Delhi somewhere");
		o1.setCity("Delhi");
		o1.setTelephone("123456789");
		table.add(o1);
	}

	/**
	 * Retrieve {@link Owner}s from the data store by last name, returning all owners
	 * whose last name <i>starts</i> with the given name.
	 * @param lastName Value to search for
	 * @return a Collection of matching {@link Owner}s (or an empty Collection if none
	 * found)
	 */
//	@Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
//	@Transactional(readOnly = true)
	Collection<Owner> findByLastName(String lastName) {
		List<Owner> owners = new ArrayList<>();
		for(Owner owner: table) {
			if (owner.getLastName().equals(lastName)) {
			    owners.add(owner);
			}
		}
		return owners;
	}

	/**
	 * Retrieve an {@link Owner} from the data store by id.
	 * @param id the id to search for
	 * @return the {@link Owner} if found
	 */
//	@Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
//	@Transactional(readOnly = true)
	Owner findById(Integer id) {
		for(Owner owner: table) {
			if (owner.getId() == id) {
				return owner;
			}
		}
		throw new RuntimeException("Owner not found");
	}

	/**
	 * Save an {@link Owner} to the data store, either inserting or updating it.
	 * @param owner the {@link Owner} to save
	 */
	void save(Owner owner) {
		table.add(owner);
	}

}
