package com.github.sunghyuk.overwatch.model;

import java.util.List;
import java.util.Map;

/**
 * Created by sunghyuk on 2016. 7. 22..
 */
public class CareerStats {

    private List<String> heroes;

    private Map<String, Map<String, PlayStats>> stats;

    public CareerStats(List<String> heroes, Map<String, Map<String, PlayStats>> stats) {
        this.heroes = heroes;
        this.stats = stats;
    }

    public List<String> getHeroes() {
        return heroes;
    }

    public Map<String, Map<String, PlayStats>> getStats() {
        return stats;
    }

    public static class Category {
        private String name;
        private PlayStats stats;

        public Category(String name, PlayStats stats) {
            this.name = name;
            this.stats = stats;
        }

        public String getName() {
            return name;
        }

        public PlayStats getStats() {
            return stats;
        }
    }
}
