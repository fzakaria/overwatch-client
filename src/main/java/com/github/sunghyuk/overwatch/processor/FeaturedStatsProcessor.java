package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.KeyValuePair;
import com.github.sunghyuk.overwatch.model.PlayStats;
import org.jsoup.select.Elements;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class FeaturedStatsProcessor implements ElementsProcessor<PlayStats> {
    @Override
    public PlayStats apply(Elements elements) {

        Map<String, String> stats = elements.select("div.card-content")
                .stream()
                .map(el -> new KeyValuePair<>(el.child(1).text(), el.child(0).text()))
                .collect(Collectors.toMap(KeyValuePair::getKey, KeyValuePair::getValue));

        return new PlayStats(stats);
    }
}
