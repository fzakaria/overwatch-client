package com.github.sunghyuk.overwatch;

import com.github.sunghyuk.overwatch.page.CareerPage;
import com.github.sunghyuk.overwatch.processor.ImageSrcProcessor;
import com.github.sunghyuk.overwatch.processor.ElementsProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class OverwatchHtmlParserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverwatchHtmlParserTest.class);

    private static final String LATEST_HTML = "fixtures/sample_en_us_02_01_2017.html";

    @Test
    public void testCareerPageJanuaryFirst2017() throws IOException {
        InputStream is = OverwatchHtmlParserTest.class.getClassLoader().getResourceAsStream(LATEST_HTML);
        Document document = Jsoup.parse(is, "utf-8", "https://playoverwatch.com");

        OverwatchHtmlParser parser = new OverwatchHtmlParser();
        CareerPage page = parser.parseCareerPage(document);
        assertThat(page, notNullValue());
        assertThat(page.getRank(), notNullValue());
        assertThat(page.getRank().getRating(), is(2342));
        LOGGER.debug(page.toJSON());
    }

    private static String parse(Elements elements, ElementsProcessor<String> function) {
        return function.apply(elements);
    }
}
