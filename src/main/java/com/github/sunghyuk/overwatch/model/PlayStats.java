package com.github.sunghyuk.overwatch.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunghyuk on 2016. 7. 20..
 */
public class PlayStats extends HashMap<String, String> {

    public PlayStats(Map<String, String> stats) {
        this.putAll(stats);
    }
}
