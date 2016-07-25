package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.CareerStats;
import com.github.sunghyuk.overwatch.model.KeyValuePair;
import com.github.sunghyuk.overwatch.model.PlayStats;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sunghyuk on 2016. 7. 22..
 */
public class CareerStatCategoryProcessor implements ElementsProcessor<CareerStats.Category> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CareerStatCategoryProcessor.class);

    @Override
    public CareerStats.Category apply(Elements elements) {
        String name = new ElementTextProcessor().apply(elements.select("th > span.stat-title"));
        Map<String, String> map = elements.select("tbody > tr")
                .stream()
                .map(el -> new KeyValuePair<>(el.child(0).text(), el.child(1).text()))
                .collect(Collectors.toMap(KeyValuePair::getKey, KeyValuePair::getValue, (v1, v2) -> {
                    LOGGER.debug("duplicated key: {}, {}", v1, v2);
                    return v2;
                }));
        return new CareerStats.Category(name, new PlayStats(map));
    }
}
