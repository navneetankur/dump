package com.iisc.pav.others;

import com.iisc.pav.owner.Owner;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private Map<String, Object> model = new HashMap<>();
    private String view;

    public ModelAndView(String view) {
        this.view = view;
    }

    public void addObject(String name, Owner owner) {
        model.put(name,owner);
    }
}
