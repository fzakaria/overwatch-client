package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.CareerStats;
import org.hamcrest.Matchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 22..
 */
public class CareerStatsProcessorTest {

    String selector = "#quick-play > section.content-box.page-wrapper.career-stats-section";

    @Test
    public void test() throws IOException {
        InputStream is = TopHeroesProcessorTest.class.getClassLoader().getResourceAsStream("fixtures/sample_quick_ko_kr.html");
        Document document = Jsoup.parse(is, "utf-8", "https://playoverwatch.com");

        CareerStatsProcessor processor = new CareerStatsProcessor();
        CareerStats stats = processor.apply(document.select(selector));
        assertThat(stats, notNullValue());
        assertThat(stats.getHeroes(), hasSize(22));
    }
}