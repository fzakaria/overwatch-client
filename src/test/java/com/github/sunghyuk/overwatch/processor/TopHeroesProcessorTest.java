package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.TopHeroes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class TopHeroesProcessorTest {

    String selector = "#quick-play > section.content-box.page-wrapper.hero-comparison-section > div";

    @Test
    public void test() throws IOException {
        InputStream is = TopHeroesProcessorTest.class.getClassLoader().getResourceAsStream("fixtures/sample_quick_ko_kr.html");
        Document document = Jsoup.parse(is, "utf-8", "https://playoverwatch.com");

        TopHeroesProcessor processor = new TopHeroesProcessor();
        TopHeroes heroes = processor.apply(document.select(selector));

        assertThat(heroes, notNullValue());
        assertThat(heroes.getOptions(), hasSize(7));
        assertThat(heroes.getData(), hasKey("승률"));
    }
}