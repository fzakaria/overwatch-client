package com.github.sunghyuk.overwatch.processor;

import org.jsoup.select.Elements;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class LevelProcessor implements ElementsProcessor<Integer> {
    @Override
    public Integer apply(Elements elements) {
        int level = new Integer(elements.select("div:eq(0)").get(0).text());
        if (!elements.select("div:eq(1)").isEmpty()) {
            String style = elements.select("div:eq(1)").get(0).attr("style");
            if (style.contains("0x0250000000000928_Rank")) {
                level += 100;
            }
        }

        return level;
    }
}
