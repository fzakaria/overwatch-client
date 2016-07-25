package com.github.sunghyuk.overwatch.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sunghyuk.overwatch.model.Achievement;
import org.hamcrest.Matchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 25..
 */
public class AchievementsCategoryProcessorTest {

    String html = "<div data-group-id=\"achievements\" data-category-id=\"overwatch.achievementCategory.0\" class=\"toggle-display u-relative is-active\">" +
            "<ul class=\"row\">" +
            "<div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\"><div data-tooltip=\"achievement-0x0E60000000000156\" class=\"media-card m-hoverable m-clickable u-margin-bottom-md\">" +
            "<div class=\"container\"><img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E60000000000156.png\" class=\"media\">" +
            "<div class=\"content\">Centenary</div></div></div><div id=\"achievement-0x0E60000000000156\" class=\"tooltip-media-card\">" +
            "<span class=\"arrow\"></span><h6 class=\"h5\">Centenary</h6><p class=\"h6\">Win 100 games in Quick or Competitive Play.</p></div></div>" +
            "<div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\"><div data-tooltip=\"achievement-0x0E60000000000157\" class=\"media-card m-hoverable m-clickable u-margin-bottom-md\">" +
            "<div class=\"container\"><img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E60000000000157.png\" class=\"media\"><div class=\"content\">Level 10</div></div></div>" +
            "<div id=\"achievement-0x0E60000000000157\" class=\"tooltip-media-card\"><span class=\"arrow\"></span><h6 class=\"h5\">Level 10</h6><p class=\"h6\">Reach level 10.</p></div></div>" +
            "<div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\"><div data-tooltip=\"achievement-0x0E60000000000158\" class=\"media-card m-hoverable m-clickable u-margin-bottom-md\">" +
            "<div class=\"container\"><img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E60000000000158.png\" class=\"media\"><div class=\"content\">Level 25</div></div></div>" +
            "<div id=\"achievement-0x0E60000000000158\" class=\"tooltip-media-card\"><span class=\"arrow\"></span><h6 class=\"h5\">Level 25</h6><p class=\"h6\">Reach level 25.</p></div></div>" +
            "<div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\"><div data-tooltip=\"achievement-0x0E60000000000159\" class=\"media-card m-hoverable m-clickable u-margin-bottom-md\">" +
            "<div class=\"container\"><img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E60000000000159.png\" class=\"media\"><div class=\"content\">Level 50</div></div></div>" +
            "<div id=\"achievement-0x0E60000000000159\" class=\"tooltip-media-card\"><span class=\"arrow\"></span><h6 class=\"h5\">Level 50</h6><p class=\"h6\">Reach level 50.</p></div></div>" +
            "<div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\"><div data-tooltip=\"achievement-0x0E6000000000015D\" class=\"media-card m-hoverable m-clickable u-margin-bottom-md\">" +
            "<div class=\"container\"><img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E6000000000015D.png\" class=\"media\"><div class=\"content\">Undying</div></div></div>" +
            "<div id=\"achievement-0x0E6000000000015D\" class=\"tooltip-media-card\"><span class=\"arrow\"></span><h6 class=\"h5\">Undying</h6>" +
            "<p class=\"h6\">Get a 20 player kill streak in Quick or Competitive Play.</p></div></div><div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\">" +
            "<div data-tooltip=\"achievement-0x0E6000000000015F\" class=\"media-card m-hoverable m-clickable u-margin-bottom-md\"><div class=\"container\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E6000000000015F.png\" class=\"media\"><div class=\"content\">Survival Expert</div></div></div>" +
            "<div id=\"achievement-0x0E6000000000015F\" class=\"tooltip-media-card\"><span class=\"arrow\"></span><h6 class=\"h5\">Survival Expert</h6>" +
            "<p class=\"h6\">Use health packs to heal 900 health in a single life in Quick or Competitive Play.</p></div></div><div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\">" +
            "<div data-tooltip=\"achievement-0x0E60000000000160\" class=\"media-card m-hoverable m-clickable u-margin-bottom-md\"><div class=\"container\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E60000000000160.png\" class=\"media\"><div class=\"content\">Decorated</div></div></div>" +
            "<div id=\"achievement-0x0E60000000000160\" class=\"tooltip-media-card\"><span class=\"arrow\"></span><h6 class=\"h5\">Decorated</h6>" +
            "<p class=\"h6\">Earn 50 postgame medals in Quick or Competitive Play.</p></div></div><div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\">" +
            "<div data-tooltip=\"achievement-0x0E60000000000161\" class=\"media-card m-hoverable m-clickable u-margin-bottom-md\"><div class=\"container\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E60000000000161.png\" class=\"media\"><div class=\"content\">Blackjack</div></div></div>" +
            "<div id=\"achievement-0x0E60000000000161\" class=\"tooltip-media-card\"><span class=\"arrow\"></span><h6 class=\"h5\">Blackjack</h6>" +
            "<p class=\"h6\">Earn 21 postgame cards in Quick or Competitive Play.</p></div></div><div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\">" +
            "<div data-tooltip=\"achievement-0x0E60000000000192\" class=\"media-card m-hoverable m-clickable u-margin-bottom-md\"><div class=\"container\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E60000000000192.png\" class=\"media\"><div class=\"content\">The Friend Zone</div></div></div>" +
            "<div id=\"achievement-0x0E60000000000192\" class=\"tooltip-media-card\" style=\"left: 0px;\"><span class=\"arrow\" style=\"left: 88px;\"></span>" +
            "<h6 class=\"h5\">The Friend Zone</h6><p class=\"h6\">Play a Quick or Competitive Play game in a group with a friend.</p></div></div>" +
            "<div class=\"column xs-6 sm-6 md-4 lg-3 xl-2\"><div data-tooltip=\"achievement-0x0E6000000000015E\" class=\"media-card m-disabled m-clickable u-margin-bottom-md\">" +
            "<div class=\"container\"><img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E6000000000015E.png\" class=\"media\"" +
            "><div class=\"content\">The Path Is Closed</div></div></div><div id=\"achievement-0x0E6000000000015E\" class=\"tooltip-media-card\" style=\"left: 240px;\">" +
            "<span class=\"arrow\" style=\"left: 88px;\"></span><h6 class=\"h5\">The Path Is Closed</h6>" +
            "<p class=\"h6\">Destroy 3 of Symmetra's Teleporters in a single Quick or Competitive Play game.</p></div></div><div class=\"column xs-6 sm-6 md-4 lg-3 xl-2 end\">" +
            "<div data-tooltip=\"achievement-0x0E60000000000162\" class=\"media-card m-disabled m-clickable u-margin-bottom-md\">" +
            "<div class=\"container\"><img src=\"https://blzgdapipro-a.akamaihd.net/game/achievements/0x0E60000000000162.png\" class=\"media\">" +
            "<div class=\"content\">Decked Out</div></div></div><div id=\"achievement-0x0E60000000000162\" class=\"tooltip-media-card\">" +
            "<span class=\"arrow\"></span><h6 class=\"h5\">Decked Out</h6><p class=\"h6\">Collect 50 unlocks for a single hero.</p></div></div></ul></div>";

    ObjectMapper mapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(AchievementsCategoryProcessorTest.class);

    @Test
    public void test() throws JsonProcessingException {
        Document document = Jsoup.parseBodyFragment(html);
        AchievementsCategoryProcessor processor = new AchievementsCategoryProcessor();
        List<Achievement> list = processor.apply(document.children());

        assertThat(list, notNullValue());
        assertThat(list, hasSize(11));
        assertThat(list.get(0).getName(), is("Centenary"));

        logger.debug("{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));
    }
}