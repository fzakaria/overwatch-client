package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.Rank;
import org.jsoup.select.Elements;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class RankProcessor implements ElementsProcessor<Rank> {
    @Override
    public Rank apply(Elements elements) {
        if (elements.isEmpty()) {
            return null;
        }
        String img = elements.select("img").get(0).attr("src");
        int rating = new Integer(elements.select("div").get(0).text());
        return new Rank(img, rating);
    }
}
