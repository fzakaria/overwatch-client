package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.Achievement;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sunghyuk on 2016. 7. 25..
 */
public class AchievementsCategoryProcessor implements ElementsProcessor<List<Achievement>> {
    @Override
    public List<Achievement> apply(Elements elements) {

        return elements.select("ul > div").stream().map(el -> {
            String img = new ImageSrcProcessor().apply(el.child(0).select("img"));
            String name = new ElementTextProcessor().apply(el.child(1).select("h6"));
            String desc = new ElementTextProcessor().apply(el.child(1).select("p"));
            boolean achieved = el.child(0).hasClass("m-disabled") ? false : true;
            return new Achievement(name, img, desc, achieved);
        }).collect(Collectors.toList());
    }
}
