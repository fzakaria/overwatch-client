package com.github.sunghyuk.overwatch.processor;

import org.jsoup.select.Elements;

import java.util.function.Function;

/**
 * Created by sunghyuk on 2016. 7. 19..
 */
public interface ElementsProcessor<R> extends Function<Elements, R> {

}
