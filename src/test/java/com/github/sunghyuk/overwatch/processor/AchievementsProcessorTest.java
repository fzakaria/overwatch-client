package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.Achievements;
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
 * Created by sunghyuk on 2016. 7. 25..
 */
public class AchievementsProcessorTest {

    String selector = "#achievements-section";

    @Test
    public void test() throws IOException {
        InputStream is = AchievementsProcessorTest.class.getClassLoader().getResourceAsStream("fixtures/sample_quick_ko_kr.html");
        Document document = Jsoup.parse(is, "utf-8", "https://playoverwatch.com");

        AchievementsProcessor processor = new AchievementsProcessor();
        Achievements achievements = processor.apply(document.select(selector));

        assertThat(achievements, notNullValue());
        assertThat(achievements.getCategories(), hasSize(6));
        assertThat(achievements.get("수비"), notNullValue());
        assertThat(achievements.get("수비"), hasSize(12));
    }
}