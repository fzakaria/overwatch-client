package com.github.sunghyuk.overwatch.processor;

import org.hamcrest.CoreMatchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class ElementTextProcessorTest {

    @Test
    public void test() {

        Document document = Jsoup.parseBodyFragment("<h1 class=\"header-masthead\">MiGArHaN</h1>");
        ElementTextProcessor processor = new ElementTextProcessor();
        String value = processor.apply(document.body().getAllElements());
        assertThat(value, is("MiGArHaN"));
    }
}