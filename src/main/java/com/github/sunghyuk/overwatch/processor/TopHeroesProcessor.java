package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.HeroComparisonData;
import com.github.sunghyuk.overwatch.model.TopHeroes;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class TopHeroesProcessor implements ElementsProcessor<TopHeroes> {
    @Override
    public TopHeroes apply(Elements elements) {

        SelectOptionProcessor selectOptionProcessor = new SelectOptionProcessor();
        Map<String, String> select = selectOptionProcessor.apply(elements.select("select"));

        List<String> options = new ArrayList<>(select.keySet());

        Map<String, HeroComparisonData> listMap = new HashMap<>();

        for (Entry<String, String> entry : select.entrySet()) {
            Elements compEls = elements.select("div[data-category-id=" + entry.getValue() + "]");
            HeroComparisonProcessor processor = new HeroComparisonProcessor();
            HeroComparisonData list = processor.apply(compEls);
            listMap.put(entry.getKey(), list);
        }

        return new TopHeroes(options, listMap);
    }
}
