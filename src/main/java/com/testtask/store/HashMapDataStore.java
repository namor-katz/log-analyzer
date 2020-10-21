package com.testtask.store;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapDataStore implements DataStore {

    private static final HashMapDataStore INSTANCE = new HashMapDataStore();
    private static final ConcurrentHashMap<Long, Long> statisticsMap = new ConcurrentHashMap();

    private HashMapDataStore() {};

    public static HashMapDataStore getInstance() {
        return INSTANCE;
    }

    @Override
    public Long compute(Long key) {
        return statisticsMap.compute(key, (k, v) -> v == null ? 1 : ++v);
    }

    @Override
    public Long get(Long key) {
        return statisticsMap.get(key);
    }

    @Override
    public Set<Long> keySet() {
        return statisticsMap.keySet();
    }

    @Override
    public void clear() {
        statisticsMap.clear();
    }

}
