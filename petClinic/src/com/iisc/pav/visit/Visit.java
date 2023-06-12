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
package com.iisc.pav.visit;

import com.iisc.pav.model.BaseEntity;

import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 * @author Dave Syer
 */
//@Entity
//@Table(name = "visits")
public class Visit extends BaseEntity {

//	@Column(name = "visit_date")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

//	@NotEmpty
//	@Column(name = "description")
	private String description;

//	@Column(name = "pet_id")
	private Integer petId;

	public Visit(int id, LocalDate date, String description, Integer petId) {
		this.setId(id);
		this.date = date;
		this.description = description;
		this.petId = petId;
	}

	/**
	 * Creates a new instance of Visit for the current date
	 */
	public Visit() {
		this.date = LocalDate.now();
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPetId() {
		return this.petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	@Override
	public boolean isValid() {
		return true;
	}
}
