package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.CareerStats;
import com.github.sunghyuk.overwatch.model.PlayStats;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunghyuk on 2016. 7. 22..
 */
public class CareerStatsProcessor implements ElementsProcessor<CareerStats> {
    @Override
    public CareerStats apply(Elements elements) {
        SelectOptionProcessor selectOptionProcessor = new SelectOptionProcessor();
        Map<String, String> heroOptions = selectOptionProcessor.apply(elements.select("select[data-group-id=stats]"));

        List<String> heroes = new ArrayList<>(heroOptions.keySet());
        Map<String, Map<String, PlayStats>> stats = new HashMap<>();
        for(Map.Entry<String, String> entry : heroOptions.entrySet()) {
            Elements tables = elements.select("div[data-category-id=" + entry.getValue() + "] > div > div.card-stat-block");
            Map<String, PlayStats> cards = new HashMap<>();
            for (Element el : tables) {
                CareerStatCategoryProcessor categoryProcessor = new CareerStatCategoryProcessor();
                CareerStats.Category category = categoryProcessor.apply(el.select("table.data-table"));
                cards.put(category.getName(), category.getStats());
            }
            stats.put(entry.getKey(), cards);
        }
        return new CareerStats(heroes, stats);
    }
}
