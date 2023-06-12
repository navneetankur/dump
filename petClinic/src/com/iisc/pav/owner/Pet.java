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

//import org.springframework.beans.support.MutableSortDefinition;
//import org.springframework.beans.support.PropertyComparator;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.samples.petclinic.model.NamedEntity;
//import org.springframework.samples.petclinic.visit.Visit;
//
//import javax.persistence.*;
import com.iisc.pav.model.NamedEntity;
import com.iisc.pav.others.VisitDateComparator;
import com.iisc.pav.visit.Visit;

import java.time.LocalDate;
import java.util.*;

/**
 * Simple business object representing a pet.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
//@Entity
//@Table(name = "pets")
public class Pet extends NamedEntity {

//	@Column(name = "birth_date")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

//	@ManyToOne
//	@JoinColumn(name = "type_id")
	private PetType type;

//	@ManyToOne
//	@JoinColumn(name = "owner_id")
	private Owner owner;

//	@Transient
	private Set<Visit> visits = new LinkedHashSet<>();

	public Pet() {

	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	public PetType getType() {
		return this.type;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	public Owner getOwner() {
		return this.owner;
	}

	protected void setOwner(Owner owner) {
		this.owner = owner;
	}

	protected Set<Visit> getVisitsInternal() {
		if (this.visits == null) {
			this.visits = new HashSet<>();
		}
		return this.visits;
	}

	protected void setVisitsInternal(Collection<Visit> visits) {
		this.visits = new LinkedHashSet<>(visits);
	}

	public List<Visit> getVisits() {
		List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
//		PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
        sortedVisits.sort(new VisitDateComparator());
		return Collections.unmodifiableList(sortedVisits);
	}

	public void addVisit(Visit visit) {
		getVisitsInternal().add(visit);
		visit.setPetId(this.getId());
	}

	public Pet(int id, LocalDate birthDate, PetType type, Owner owner) {
		this.setId(id);
		this.birthDate = birthDate;
		this.type = type;
		this.owner = owner;
	}

	@Override
	public boolean isValid() {
		return true;
	}
}
