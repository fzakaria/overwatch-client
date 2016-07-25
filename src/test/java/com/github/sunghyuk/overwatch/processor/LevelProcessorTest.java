package com.github.sunghyuk.overwatch.processor;

import org.hamcrest.Matchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class LevelProcessorTest {

    String html = "<div style=\"background-image:url(https://blzgdapipro-a.akamaihd.net/game/playerlevelrewards/0x0250000000000928_Border.png)\" class=\"player-level\">" +
            "<div class=\"u-vertical-center\">62</div>" +
            "<div style=\"background-image:url(https://blzgdapipro-a.akamaihd.net/game/playerlevelrewards/0x0250000000000928_Rank.png)\" class=\"player-rank\"></div>" +
            "</div>";

    @Test
    public void test() {
        Document document = Jsoup.parseBodyFragment(html);
        LevelProcessor processor = new LevelProcessor();
        Integer value = processor.apply(document.body().children());
        assertThat(value, is(162));
    }

}