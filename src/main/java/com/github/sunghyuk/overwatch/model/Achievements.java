package com.github.sunghyuk.overwatch.model;

import java.util.List;
import java.util.Map;

/**
 * Created by sunghyuk on 2016. 7. 25..
 */
public class Achievements {

    private List<String> categories;

    private Map<String, List<Achievement>> list;

    public Achievements(List<String> categories, Map<String, List<Achievement>> list) {
        this.categories = categories;
        this.list = list;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<Achievement> get(String key) {
        return list.get(key);
    }

}
