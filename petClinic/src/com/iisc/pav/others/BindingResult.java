package com.iisc.pav.others;

import com.iisc.pav.owner.Owner;
import com.iisc.pav.owner.Pet;
import com.iisc.pav.visit.Visit;

public class BindingResult {

    public boolean hasErrors(Owner owner) {
        return owner.getPets() != null;
    }

    public void rejectValue(String lastName, String notFound, String not_found) {

    }

    public boolean hasErrors(Pet pet) {
        return pet.getOwner() != null;
    }

    public boolean hasErrors(Visit visit) {
        return visit.getPetId() > 0;
    }
}
