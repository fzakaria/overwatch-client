package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.KeyValuePair;
import org.jsoup.select.Elements;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class SelectOptionProcessor implements ElementsProcessor<Map<String, String>> {
    @Override
    public Map<String, String> apply(Elements elements) {
        return elements.select("option")
                .stream()
                .map(el -> new KeyValuePair<>(el.text(), el.val()))
                .collect(Collectors.toMap(KeyValuePair::getKey, KeyValuePair::getValue));
    }
}
