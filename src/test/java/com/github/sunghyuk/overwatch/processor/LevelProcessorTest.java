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

    @Test
    public void testGetImageName() {
        String s = "background-image:url(https://blzgdapipro-a.akamaihd.net/game/playerlevelrewards/0x0250000000000928_Rank.png)";
        LevelProcessor processor = new LevelProcessor();
        String imageName = processor.getImageName(s);
        assertThat(imageName, is("0x0250000000000928"));
    }

    @Test
    public void testGetLevelByImage() {
        LevelProcessor processor = new LevelProcessor();
        int rank = processor.getLevelByImage("0x0250000000000922");
        assertThat(rank, is(100));
        rank = processor.getLevelByImage("0x025000000000093F");
        assertThat(rank, is(200));
        rank = processor.getLevelByImage("0x0250000000000940");
        assertThat(rank, is(300));
        rank = processor.getLevelByImage("0x0250000000000953");
        assertThat(rank, is(400));
        rank = processor.getLevelByImage("0x025000000000094B");
        assertThat(rank, is(500));
        rank = processor.getLevelByImage("0x025000000000095F");
        assertThat(rank, is(600));
    }
}