package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.Rank;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class RankProcessorTest {

    String html = "<div class=\"competitive-rank\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/rank-icons/rank-9.png\">" +
            "<div class=\"u-align-center h6\">82</div>" +
            "</div>";

    @Test
    public void test() {
        Document document = Jsoup.parseBodyFragment(html);
        RankProcessor processor = new RankProcessor();
        Rank rank = processor.apply(document.children());
        assertThat(rank, notNullValue());
        assertThat(rank.getRating(), is(82));
        assertThat(rank.getImage(), is("https://blzgdapipro-a.akamaihd.net/game/rank-icons/rank-9.png"));
    }
}