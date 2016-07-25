package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.Hero;
import com.github.sunghyuk.overwatch.model.HeroComparisonData;
import com.github.sunghyuk.overwatch.model.HeroComparisonData.Entry;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sunghyuk on 2016. 7. 22..
 */
public class HeroComparisonProcessor implements ElementsProcessor<HeroComparisonData> {

    @Override
    public HeroComparisonData apply(Elements elements) {
        List<Entry> list = elements.select("div.progress-category-item").stream().map(el -> {
            String image = new ImageSrcProcessor().apply(el.select("img"));
            String name = new ElementTextProcessor().apply(el.select("div.title"));
            String value = new ElementTextProcessor().apply(el.select("div.description"));
            return new Entry(new Hero(name, image), value);
        }).collect(Collectors.toList());
        return new HeroComparisonData(list);
    }
}
