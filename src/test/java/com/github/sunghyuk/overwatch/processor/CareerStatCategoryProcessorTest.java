package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.CareerStats;
import org.hamcrest.Matchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 22..
 */
public class CareerStatCategoryProcessorTest {

    String html = "<table class=\"data-table\"><thead><tr><th colspan=\"2\"><svg viewBox=\"0 0 32 32\" class=\"icon\">\n" +
            "\t\t\t<path d=\"M2.3,29.2l3,0.7l2-2.2l-0.3-1c0.1-0.1,0.2-0.3,0.3-0.4c1.3-1.4-0.7,0.6,0.7-0.7\n" +
            "\t\t        c0.5-0.5,1-1,1.5-1.4C9.5,24,9.6,24,9.7,24c0,0,0,0,0,0c0.1,0,0.2,0.1,0.3,0.1c0.9,0.9,0.8,0.7,1.7,1.6l0,0c0.3,0.3,0.6,0.6,0.9,0.9\n" +
            "\t\t        c0.5,0.5,1.1,1,1.6,1.5c0.1,0.1,0.2,0.2,0.3,0.1c0.1-0.1,0-0.2,0-0.3c-0.2-0.6-0.4-1.2-0.6-1.8c-0.2-0.5-0.3-1-0.5-1.4\n" +
            "\t\t        c-0.1-0.4-0.3-0.8-0.4-1.1c-0.1-0.2-0.2-0.4-0.3-0.5c-0.3-0.3,0.4,0.4,0.1,0.1c-0.1-0.1-0.2-0.2-0.2-0.2c0-0.1,0-0.2,0.1-0.3\n" +
            "\t\t        c0.8-0.8,1.6-1.6,2.4-2.5c1.7-1.7,3.4-3.4,5.1-5.1l0,0l-4,4l0,0c0.1-0.1,0.2-0.2,0.3-0.3c2.4-2.4,4.8-4.7,7.2-7.1l0,0\n" +
            "\t\t        c1.1-1.1,2.3-2.3,3.4-3.4c0.4-0.4,0.6-0.8,0.7-1.3c0.2-1.2,0.7-4,0.6-4.1c-0.1-0.1-3.5,0.4-4.6,0.6c-0.2,0-0.4,0.1-0.5,0.3\n" +
            "\t\t        c-3.8,3.8-7.5,7.5-11.3,11.3l0,0l4-4l0,0c-2.5,2.5-5,5-7.4,7.4c-0.1,0.1-0.2,0.1-0.2,0.1c-0.1,0-0.1,0-0.2-0.1\n" +
            "\t\t        c-0.3-0.3,0.5,0.5,0.3,0.3c-0.3-0.3-0.7-0.5-1.1-0.7c-1.3-0.4-2.6-0.9-3.8-1.3c-0.1,0-0.1,0-0.2-0.1c-0.1,0-0.2-0.1-0.3,0\n" +
            "\t\t        c-0.1,0.1,0,0.2,0.1,0.3c0.3,0.3,0.5,0.6,0.8,0.9c1.1,1.1,1.1,1.1,2.2,2.2c0.4,0.4,0.7,0.7,1.1,1.1c0.2,0.2,0.3,0.3,0.3,0.4\n" +
            "\t\t        s-0.1,0.2-0.2,0.4c-1.1,1.2-0.1,0.2-1.3,1.4c-0.6,0.6-0.1,0.1-0.7,0.7c-0.2,0.2-0.3,0.3-0.5,0.4L3.4,24l-2,2.2L2.3,29.2z\"></path>\n" +
            "\t\t\t<path d=\"M29.7,29.2l-3,0.7l-2-2.2l0.3-1c-0.1-0.1-0.2-0.3-0.3-0.4c-1.3-1.4,0.7,0.6-0.7-0.7\n" +
            "\t\t        c-0.5-0.5-1-1-1.5-1.4C22.5,24,22.4,24,22.3,24c0,0,0,0,0,0c-0.1,0-0.2,0.1-0.3,0.1c-0.9,0.9-0.8,0.7-1.7,1.6l0,0\n" +
            "\t\t        c-0.3,0.3-0.6,0.6-0.9,0.9c-0.5,0.5-1.1,1-1.6,1.5c-0.1,0.1-0.2,0.2-0.3,0.1c-0.1-0.1,0-0.2,0-0.3c0.2-0.6,0.4-1.2,0.6-1.8\n" +
            "\t\t        c0.2-0.5,0.3-1,0.5-1.4c0.1-0.4,0.3-0.8,0.4-1.1c0.1-0.2,0.2-0.4,0.3-0.5c0.3-0.3-0.4,0.4-0.1,0.1c0.1-0.1,0.2-0.2,0.2-0.2\n" +
            "\t\t        c0-0.1,0-0.2-0.1-0.3c-0.8-0.8-1.6-1.6-2.4-2.5c-1.7-1.7-3.4-3.4-5.1-5.1l0,0l4,4l0,0c-0.1-0.1-0.2-0.2-0.3-0.3\n" +
            "\t\t        c-2.4-2.4-4.8-4.7-7.2-7.1l0,0c-1.1-1.1-2.3-2.3-3.4-3.4C4.4,7.9,4.2,7.5,4.1,7C4,5.8,3.5,2.9,3.6,2.8c0.1-0.1,3.5,0.4,4.6,0.6\n" +
            "\t\t        c0.2,0,0.4,0.1,0.5,0.3c3.8,3.8,7.5,7.5,11.3,11.3l0,0l-4-4l0,0c2.5,2.5,5,5,7.4,7.4c0.1,0.1,0.2,0.1,0.2,0.1c0.1,0,0.1,0,0.2-0.1\n" +
            "\t\t        c0.3-0.3-0.5,0.5-0.3,0.3c0.3-0.3,0.7-0.5,1.1-0.7c1.3-0.4,2.6-0.9,3.8-1.3c0.1,0,0.1,0,0.2-0.1c0.1,0,0.2-0.1,0.3,0\n" +
            "\t\t        c0.1,0.1,0,0.2-0.1,0.3c-0.3,0.3-0.5,0.6-0.8,0.9c-1.1,1.1-1.1,1.1-2.2,2.2c-0.4,0.4-0.7,0.7-1.1,1.1c-0.2,0.2-0.3,0.3-0.3,0.4\n" +
            "\t\t        c0,0.1,0.1,0.2,0.2,0.4c1.1,1.2,0.1,0.2,1.3,1.4c0.6,0.6,0.1,0.1,0.7,0.7c0.2,0.2,0.3,0.3,0.5,0.4l1.1-0.2l2,2.3L29.7,29.2z\"></path>\n" +
            "\t\t</svg><span class=\"stat-title\">Combat</span></th></tr></thead>" +
            "<tbody>" +
            "<tr><td>Melee Final Blows</td><td>192</td></tr><tr><td>Solo Kills</td><td>2,217</td></tr>" +
            "<tr><td>Objective Kills</td><td>2,858</td></tr><tr><td>Final Blows</td><td>7,392</td>" +
            "</tr><tr><td>Damage Done</td><td>4,575,485</td></tr><tr><td>Eliminations</td><td>12,514</td></tr>" +
            "<tr><td>Environmental Kills</td><td>18</td></tr><tr><td>Multikills</td><td>119</td></tr></tbody></table>";

    Logger logger = LoggerFactory.getLogger(CareerStatCategoryProcessorTest.class);

    @Test
    public void test() {
        Document document = Jsoup.parseBodyFragment(html);
        CareerStatCategoryProcessor processor = new CareerStatCategoryProcessor();
        CareerStats.Category category = processor.apply(document.children());
        assertThat(category, notNullValue());
        assertThat(category.getName(), is("Combat"));
        assertThat(category.getStats(), hasKey("Melee Final Blows"));
        assertThat(category.getStats(), hasValue("2,858"));

        logger.debug("{}", category.getName());
        logger.debug("{}", category.getStats());
    }

}