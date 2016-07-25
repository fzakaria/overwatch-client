package com.github.sunghyuk.overwatch.processor;

import org.jsoup.select.Elements;

/**
 * Created by sunghyuk on 2016. 7. 19..
 */
public class ElementTextProcessor implements ElementsProcessor<String> {

    public String apply(Elements elements) {
        return elements.get(0).text();
    }
}
