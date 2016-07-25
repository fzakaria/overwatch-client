package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.Achievement;
import com.github.sunghyuk.overwatch.model.Achievements;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunghyuk on 2016. 7. 25..
 */
public class AchievementsProcessor implements ElementsProcessor<Achievements> {
    @Override
    public Achievements apply(Elements elements) {

        SelectOptionProcessor optionProcessor = new SelectOptionProcessor();
        Map<String, String> map = optionProcessor.apply(elements.select("select"));
        List<String> options = new ArrayList<>(map.keySet());

        Map<String, List<Achievement>> list = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            AchievementsCategoryProcessor categoryProcessor = new AchievementsCategoryProcessor();
            List<Achievement> catList = categoryProcessor.apply(elements.select("div[data-category-id=" + entry.getValue() + "]"));
            list.put(entry.getKey(), catList);
        }

        return new Achievements(options, list);
    }
}
