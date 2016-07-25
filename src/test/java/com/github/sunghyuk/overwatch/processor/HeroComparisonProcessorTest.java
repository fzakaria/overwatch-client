package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.HeroComparisonData;
import com.github.sunghyuk.overwatch.model.KeyValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 22..
 */
public class HeroComparisonProcessorTest {

    String html = "<div data-group-id=\"comparisons\" data-category-id=\"overwatch.guid.0x0860000000000021\" class=\"progress-category toggle-display is-partial\">" +
            "<div data-overwatch-progress-percent=\"1\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000008.png\">" +
            "<div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(39, 120, 226) 0px 0.2rem 0px 0px inset; right: 0px; background-color: rgb(27, 101, 198);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">파라</div><div class=\"description\">18 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.5520475640116085\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E000000000006E.png\">" +
            "<div class=\"bar-container\"><div style=\"box-shadow: rgb(117, 136, 195) 0px 0.2rem 0px 0px inset; right: 44.7952%; background-color: rgb(88, 112, 182);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">솔저: 76</div><div class=\"description\">10 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.5491270423058208\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000029.png\">" +
            "<div class=\"bar-container\"><div style=\"box-shadow: rgb(152, 254, 42) 0px 0.2rem 0px 0px inset; right: 45.0873%; background-color: rgb(132, 254, 1);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">겐지</div><div class=\"description\">9 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.5308530978442754\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000068.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(248, 151, 191) 0px 0.2rem 0px 0px inset; right: 46.9147%; background-color: rgb(245, 113, 168);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">자리야</div><div class=\"description\">9 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.4035478914998837\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000042.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(170, 69, 69) 0px 0.2rem 0px 0px inset; right: 59.6452%; background-color: rgb(141, 57, 57);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">맥크리</div><div class=\"description\">7 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.32866747207077235\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000003.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(250, 164, 67) 0px 0.2rem 0px 0px inset; right: 67.1333%; background-color: rgb(248, 145, 27);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">트레이서</div><div class=\"description\">5 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.2909023044767272\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000002.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(60, 60, 57) 0px 0.2rem 0px 0px inset; right: 70.9098%; background-color: rgb(39, 39, 37);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">리퍼</div><div class=\"description\">5 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.10365204330305565\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000040.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(206, 170, 147) 0px 0.2rem 0px 0px inset; right: 89.6348%; background-color: rgb(193, 148, 119);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">로드호그</div><div class=\"description\">1 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.08115231019080334\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E000000000000A.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(138, 138, 189) 0px 0.2rem 0px 0px inset; right: 91.8848%; background-color: rgb(111, 111, 174);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">위도우메이커</div><div class=\"description\">1 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.06375463062655233\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000009.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(95, 100, 114) 0px 0.2rem 0px 0px inset; right: 93.6245%; background-color: rgb(76, 80, 92);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">윈스턴</div><div class=\"description\">1 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.06320241108790631\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000004.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(255, 234, 149) 0px 0.2rem 0px 0px inset; right: 93.6798%; background-color: rgb(255, 225, 108);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">메르시</div><div class=\"description\">1 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.06201702416350458\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000005.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(173, 160, 87) 0px 0.2rem 0px 0px inset; right: 93.7983%; background-color: rgb(147, 136, 72);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">한조</div><div class=\"description\">1 시간</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.035764954202039234\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000065.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(245, 172, 15) 0px 0.2rem 0px 0px inset; right: 96.4235%; background-color: rgb(211, 147, 8);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">정크랫</div><div class=\"description\">39 분</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.018420842902569425\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000007.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(187, 171, 165) 0px 0.2rem 0px 0px inset; right: 98.1579%; background-color: rgb(170, 149, 142);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">라인하르트</div><div class=\"description\">20 분</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.018183769366638933\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000020.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(240, 188, 0) 0px 0.2rem 0px 0px inset; right: 98.1816%; background-color: rgb(199, 156, 0);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">젠야타</div><div class=\"description\">19 분</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.018036519129428333\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000015.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(130, 175, 95) 0px 0.2rem 0px 0px inset; right: 98.1963%; background-color: rgb(110, 153, 77);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">바스티온</div><div class=\"description\">19 분</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.009461678132541772\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000079.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(158, 240, 72) 0px 0.2rem 0px 0px inset; right: 99.0538%; background-color: rgb(139, 236, 34);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">루시우</div><div class=\"description\">10 분</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0.007103282737086824\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E000000000007A.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(255, 168, 224) 0px 0.2rem 0px 0px inset; right: 99.2897%; background-color: rgb(255, 127, 209);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">D.Va</div><div class=\"description\">7 분</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000006.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(255, 123, 40) 0px 0.2rem 0px 0px inset; right: 100%; background-color: rgb(255, 98, 0);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">토르비욘</div><div class=\"description\">--</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E0000000000016.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(133, 241, 255) 0px 0.2rem 0px 0px inset; right: 100%; background-color: rgb(92, 236, 255);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">시메트라</div><div class=\"description\">--</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E00000000000DD.png\"><div class=\"bar-container\">" +
            "<div style=\"box-shadow: rgb(191, 232, 248) 0px 0.2rem 0px 0px inset; right: 100%; background-color: rgb(154, 219, 244);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">메이</div><div class=\"description\">--</div></div></div></div>" +
            "<div data-overwatch-progress-percent=\"0\" class=\"progress-2 m-animated progress-category-item\">" +
            "<img src=\"https://blzgdapipro-a.akamaihd.net/game/heroes/small/0x02E000000000013B.png\">" +
            "<div class=\"bar-container\"><div style=\"box-shadow: rgb(250, 164, 67) 0px 0.2rem 0px 0px inset; right: 100%; background-color: rgb(248, 145, 27);\" class=\"bar\"></div>" +
            "<div class=\"bar-text\"><div class=\"title\">아나</div><div class=\"description\">--</div></div></div></div></div>";

    @Test
    public void test() {
        Document document = Jsoup.parseBodyFragment(html);
        HeroComparisonProcessor processor = new HeroComparisonProcessor();
        HeroComparisonData data = processor.apply(document.children());
        assertThat(data, notNullValue());
        assertThat(data, hasSize(22));
        assertThat(data.get(0).getKey().getName(), is("파라"));

    }
}