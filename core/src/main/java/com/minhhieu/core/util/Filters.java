package com.minhhieu.core.util;


import com.minhhieu.commons.util.CriteriaCreator;
import com.minhhieu.commons.model.filter.PersonFilter;
import org.springframework.data.relational.core.query.Criteria;

import static com.minhhieu.commons.util.CriteriaUtil.*;


public class Filters {

    private Filters() {
    }

    public static Criteria toCriteria(PersonFilter filter) {
        return new CriteriaCreator()
                .and(contain("name", filter.getName()))
                .and(equal("id", filter.getId()))
                .and(in("status", filter.getStatuses()))
                .create();
    }

}
