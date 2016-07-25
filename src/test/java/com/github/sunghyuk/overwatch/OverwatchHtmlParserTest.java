package com.github.sunghyuk.overwatch;

import com.github.sunghyuk.overwatch.page.CareerPage;
import com.github.sunghyuk.overwatch.processor.ImageSrcProcessor;
import com.github.sunghyuk.overwatch.processor.ElementsProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 18..
 */
public class OverwatchHtmlParserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverwatchHtmlParserTest.class);

    @Test
    public void testCareerPage() throws IOException, URISyntaxException {
        InputStream is = OverwatchHtmlParserTest.class.getClassLoader().getResourceAsStream("fixtures/sample_en_us.html");
        Document document = Jsoup.parse(is, "utf-8", "https://playoverwatch.com");

        OverwatchHtmlParser parser = new OverwatchHtmlParser();
        CareerPage page = parser.parseCareerPage(document);
        assertThat(page, notNullValue());
        //assertThat(page.getName(), is("땡구르르"));

        LOGGER.debug(page.toJSON());
    }


    @Test
    public void test() throws IOException {
        InputStream is = OverwatchHtmlParserTest.class.getClassLoader().getResourceAsStream("fixtures/sample_quick_en_us.html");
        Document document = Jsoup.parse(is, "utf-8", "https://playoverwatch.com");

        Elements el = document.select("#overview-section > div > div.page-wrapper.row.content-box > div > div > div.masthead-player > img");
        LOGGER.debug("portrait: {}", el.get(0).attr("src"));

        el = document.select("#overview-section > div > div.page-wrapper.row.content-box > div > div > div.masthead-player > h1");
        LOGGER.debug("name: {}", el.get(0).text());

        /*
        Elements elements = document.select("div[data-category-id='0x02E0000000000002'] td");
        elements.forEach(el -> {
            LOGGER.debug("{}", el.text());
        });

        Elements elements1 = document.select("#overview-section > div > div.page-wrapper.row.content-box > div > div");
        LOGGER.debug("{}", elements1.html());
        */
    }

    @Test
    public void testHandler() throws IOException {
        InputStream is = OverwatchHtmlParserTest.class.getClassLoader().getResourceAsStream("fixtures/sample_quick_en_us.html");
        Document document = Jsoup.parse(is, "utf-8", "https://playoverwatch.com");

        Elements el = document.select("#overview-section > div > div.page-wrapper.row.content-box > div > div > div.masthead-player > img");
        ElementsProcessor<String> attrHandler = new ImageSrcProcessor();
        String src = parse(el, attrHandler);
        LOGGER.debug("{}", src);
    }

    private static String parse(Elements elements, ElementsProcessor<String> function) {
        return function.apply(elements);
    }
}