package com.github.sunghyuk.overwatch.processor;

import org.hamcrest.Matchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class SelectOptionProcessorTest {

    String html = "<select data-group-id=\"comparisons\" class=\"js-career-select dropdown_original\">" +
            "<option value=\"overwatch.guid.0x0860000000000021\">플레이 시간</option>" +
            "<option value=\"overwatch.guid.0x0860000000000039\">승리한 게임</option>" +
            "<option value=\"overwatch.guid.0x08600000000003D1\">승률</option>" +
            "<option value=\"overwatch.guid.0x086000000000002F\">명중률</option>" +
            "<option value=\"overwatch.guid.0x08600000000003D2\">목숨당 처치</option>" +
            "<option value=\"overwatch.guid.0x0860000000000346\">동시 처치 - 최고기록</option>" +
            "<option value=\"overwatch.guid.0x086000000000039C\">임무 기여 처치 - 평균</option>" +
            "</select>";

    @Test
    public void test() {
        Document document = Jsoup.parseBodyFragment(html);
        SelectOptionProcessor processor = new SelectOptionProcessor();
        Map<String, String> options = processor.apply(document.children());

        assertThat(options, notNullValue());
        assertThat(options, hasKey("승률"));
    }
}