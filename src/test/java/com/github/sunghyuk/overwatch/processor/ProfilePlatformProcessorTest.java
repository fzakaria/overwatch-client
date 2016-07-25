package com.github.sunghyuk.overwatch.processor;

import com.github.sunghyuk.overwatch.model.ProfilePlatforms;
import org.hamcrest.Matchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfilePlatformProcessorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilePlatformProcessorTest.class);

    @Test
    public void test() throws URISyntaxException {
        URI file = ProfilePlatformProcessorTest.class.getClassLoader().getResource("fixtures/get_platforms.json").toURI();
        LOGGER.debug("{}", file);

        Document document = Jsoup.parseBodyFragment("<script>window.app.career.init(150477, 'pc', 'kr');\n" +
                "</script>");
        ProfilePlatformProcessor processor = Mockito.spy(new ProfilePlatformProcessor());
        Mockito.when(processor.jsonURL(Mockito.anyString())).thenReturn(file.toString());
        ProfilePlatforms platforms = processor.apply(document.select("script"));
        assertThat(platforms, Matchers.notNullValue());
        assertThat(platforms.getId(), is("150477"));
        assertThat(platforms.getPlatforms(), hasSize(5));
    }
}