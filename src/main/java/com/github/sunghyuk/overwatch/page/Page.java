package com.github.sunghyuk.overwatch.page;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by sunghyuk on 2016. 7. 20..
 */
public abstract class Page {

    static final ObjectMapper MAPPER = new ObjectMapper();

    public abstract String toJSON();

}
