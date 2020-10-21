package com.testtask.store;

import java.util.Set;

public interface DataStore {
    Long compute(Long key);
    Long get(Long key);
    Set<Long> keySet();
    void clear();
}
