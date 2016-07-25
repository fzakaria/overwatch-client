package com.github.sunghyuk.overwatch;

import com.github.sunghyuk.overwatch.page.CareerPage;
import com.github.sunghyuk.overwatch.page.Selector;
import com.github.sunghyuk.overwatch.page.SelectorGroup;
import com.github.sunghyuk.overwatch.processor.ElementsProcessor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Statement;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunghyuk on 2016. 7. 18..
 */
public class OverwatchHtmlParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverwatchHtmlParser.class);

    public CareerPage parseCareerPage(Document document) {
        CareerPage careerPage = new CareerPage();

        for (final Field field : CareerPage.class.getDeclaredFields()) {
            LOGGER.debug("field: {}", field.getName());

            Object value;
            SelectorGroup selectorGroup = field.getDeclaredAnnotation(SelectorGroup.class);
            Selector selector = field.getDeclaredAnnotation(Selector.class);
            if (selectorGroup != null) {
                value = processSelectorGroup(selectorGroup, document);
            } else if (selector != null) {
                value = processSelector(selector, document);
            } else {
                continue;
            }

            String methodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            LOGGER.debug("invoke method: {}", methodName);
            Statement statement = new Statement(careerPage, methodName, new Object[]{value});

            try {
                statement.execute();
            } catch (Exception e) {
                LOGGER.error("method invocation error", e);
            }
        }

        return careerPage;
    }

    private Object processSelectorGroup(SelectorGroup selectorGroup, Document document) {
        Map<String, Object> values = new HashMap<>();
        for (Selector sel : selectorGroup.selctors()) {
            Object value = processSelector(sel, document);
            values.put(sel.key(), value);
        }
        return values;
    }

    private Object processSelector(Selector selector, Document document) {
        Elements elements = null;
        elements = document.select(selector.value());
        //LOGGER.debug("processing: {}", elements);
        try {
            ElementsProcessor instance = selector.handler().newInstance();
            return instance.apply(elements);
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("reflection error", e);
        }
        return null;
    }
}
