package com.iisc.pav.others;

import com.iisc.pav.visit.Visit;

import java.util.Comparator;

public class VisitDateComparator implements Comparator<Visit> {
    @Override
    public int compare(Visit v1, Visit v2) {
        return v1.getDate().compareTo(v2.getDate());
    }
}
