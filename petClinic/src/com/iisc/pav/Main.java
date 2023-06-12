package com.iisc.pav;

import com.iisc.pav.others.BindingResult;
import com.iisc.pav.others.ModelMap;
import com.iisc.pav.owner.*;
import com.iisc.pav.system.CrashController;
import com.iisc.pav.system.WelcomeController;
import com.iisc.pav.vet.VetController;
import com.iisc.pav.vet.VetRepository;
import com.iisc.pav.visit.Visit;
import com.iisc.pav.visit.VisitRepository;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        new EndpointDocController().show(new ModelMap());
        OwnerController oc = new OwnerController(new OwnerRepository(), new VisitRepository());
        Owner owner = new Owner(4,"firstname","lastname","address","city","45612378");
        oc.initCreationForm(new ModelMap<>());
        oc.initFindForm(new ModelMap<>());
        oc.initUpdateOwnerForm(3,new ModelMap<>());
        oc.processCreationForm(owner,new BindingResult());
        oc.processFindForm(owner,new BindingResult(),new ModelMap<>());
        oc.processUpdateOwnerForm(owner,new BindingResult(), 45);
        oc.showOwner(3);
        PetController pc = new PetController(new PetRepository(), new OwnerRepository());
        Pet pet = new Pet(3,LocalDate.of(2020,02,15),new PetType("dog"),owner);
        pc.findOwner(3);
        pc.initCreationForm(owner,new ModelMap());
        pc.initUpdateForm(4,new ModelMap());
        pc.processCreationForm(owner,pet,new BindingResult(),new ModelMap());
        pc.processUpdateForm(pet,new BindingResult(),owner,new ModelMap());
        Visit visit = new Visit(3,LocalDate.now(),"description of visit",3);
        VisitController vc = new VisitController(new VisitRepository(),new PetRepository());
        vc.initNewVisitForm(3,new ModelMap<>());
        vc.processNewVisitForm(visit,new BindingResult());
        VetController vetC = new VetController(new VetRepository());
        vetC.showResourcesVetList();
        vetC.showVetList(new ModelMap<>());
        new WelcomeController().welcome();
        new CrashController().triggerException();
    }
}
