package com.iisc.pav.others;

import com.iisc.pav.model.NamedEntity;

import java.util.Comparator;

public class NamedEntityComparator implements Comparator<NamedEntity> {
    @Override
    public int compare(NamedEntity o1, NamedEntity o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
