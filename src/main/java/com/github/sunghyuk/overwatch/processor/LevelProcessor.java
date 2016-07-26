package com.github.sunghyuk.overwatch.processor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunghyuk on 2016. 7. 21..
 *
 * https://github.com/SunDwarf/OWAPI/blob/master/owapi/prestige.py
 *
 */
public class LevelProcessor implements ElementsProcessor<Integer> {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Map<String, Integer> levelMap;

    public LevelProcessor() {
        InputStream is = LevelProcessor.class.getClassLoader().getResourceAsStream("levelMap.json");
        try {
            levelMap = MAPPER.readValue(is, new TypeReference<Map<String, Integer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer apply(Elements elements) {
        int level = new Integer(elements.select("div:eq(0)").get(0).text());
        if (!elements.select("div:eq(1)").isEmpty()) {
            String style = elements.select("div:eq(1)").get(0).attr("style");
            String imageName = getImageName(style);
            level += getLevelByImage(imageName);
        }

        return level;
    }

    protected String getImageName(String style) {
        int begin = style.lastIndexOf("/");
        int end = style.lastIndexOf("_");
        return style.substring(begin+1, end);
    }

    protected int getLevelByImage(String imageName) {
        if (levelMap.containsKey(imageName)) {
            return levelMap.get(imageName) * 100;
        }
        return 0;
    }

}
