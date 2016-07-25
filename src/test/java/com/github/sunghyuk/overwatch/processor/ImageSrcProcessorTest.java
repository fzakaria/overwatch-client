package com.github.sunghyuk.overwatch.processor;

import org.hamcrest.CoreMatchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class ImageSrcProcessorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageSrcProcessorTest.class);

    @Test
    public void test() {
        Document document = Jsoup.parseBodyFragment("<img src=\"https://blzgdapipro-a.akamaihd.net/game/unlocks/0x02500000000008BB.png\" class=\"player-portrait\">");
        ImageSrcProcessor processor = new ImageSrcProcessor();
        LOGGER.debug("{}", document.body().getAllElements());
        String value = processor.apply(document.select("img"));
        assertThat(value, is("https://blzgdapipro-a.akamaihd.net/game/unlocks/0x02500000000008BB.png"));
    }
}