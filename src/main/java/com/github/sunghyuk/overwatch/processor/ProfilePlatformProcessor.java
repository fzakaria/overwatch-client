package com.github.sunghyuk.overwatch.processor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sunghyuk.overwatch.model.ProfilePlatform;
import com.github.sunghyuk.overwatch.model.ProfilePlatforms;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by sunghyuk on 2016. 7. 20..
 */
public class ProfilePlatformProcessor implements ElementsProcessor<ProfilePlatforms> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilePlatformProcessor.class);

    private static final String PLATFORMS_JSON_REQ_URL = "https://playoverwatch.com/ko-kr/career/get-platforms/{0}";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public ProfilePlatforms apply(Elements elements) {
        String js = elements.get(0).dataNodes().get(0).getWholeData();
        String id = js.replaceAll("(?s)^[^\\d]+(\\d+).*", "$1");

        LOGGER.debug("script: {}", js);
        LOGGER.debug("id: {}", id);

        List<ProfilePlatform> platforms = Collections.emptyList();
        try {
            platforms = MAPPER.readValue(new URL(jsonURL(id)), new TypeReference<List<ProfilePlatform>>() {});
        } catch (IOException e) {
            LOGGER.error("", e);
        }

        return new ProfilePlatforms(id, platforms);
    }

    public String jsonURL(String id) {
        return PLATFORMS_JSON_REQ_URL + id;
    }
}
