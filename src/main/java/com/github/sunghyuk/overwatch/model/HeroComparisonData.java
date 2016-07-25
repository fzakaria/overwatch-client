package com.github.sunghyuk.overwatch.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunghyuk on 2016. 7. 22..
 */
public class HeroComparisonData extends ArrayList<HeroComparisonData.Entry> {

    public HeroComparisonData(List<Entry> list) {
        super(list);
    }

    public static class Entry {
        private Hero key;

        private String value;

        public Entry(Hero key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public Hero getKey() {
            return key;
        }

    }
}
