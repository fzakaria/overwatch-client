package com.github.sunghyuk.overwatch.model;

import com.github.sunghyuk.overwatch.model.HeroComparisonData;

import java.util.List;
import java.util.Map;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class TopHeroes {

    private List<String> options;

    private Map<String, HeroComparisonData> data;

    public TopHeroes(List<String> options, Map<String, HeroComparisonData> data) {
        this.options = options;
        this.data = data;
    }

    public List<String> getOptions() {
        return options;
    }

    public Map<String, HeroComparisonData> getData() {
        return data;
    }
}
