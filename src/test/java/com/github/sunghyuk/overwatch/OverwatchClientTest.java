package com.github.sunghyuk.overwatch;

import com.github.sunghyuk.overwatch.model.Player;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by sunghyuk on 2016. 7. 20..
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class OverwatchClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverwatchClientTest.class);

    @Test
    public void testBuild() {
        OverwatchClient client = new OverwatchClient.Builder().platform("pc").locale(new Locale("ko", "KR")).build();
        assertThat(client, notNullValue());
        assertThat(client.getLocale(), is(new Locale("ko", "KR")));
        assertThat(client.getPlatform(), is("pc"));
    }

    @Test
    public void testFindPlayer() throws IOException {
        InputStream is = OverwatchHtmlParserTest.class.getClassLoader().getResourceAsStream("fixtures/sample_quick_en_us.html");
        Document document = Jsoup.parse(is, "utf-8", "https://playoverwatch.com");

        Connection connection = Mockito.mock(Connection.class);
        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);
        Mockito.when(connection.get()).thenReturn(document);

        OverwatchClient client = new OverwatchClient.Builder().platform("pc").locale(new Locale("ko", "KR")).build();
        Optional<Player> optional = client.findPlayer("abc#12345");
        Player player = optional.get();

        assertThat(player, notNullValue());
        assertThat(player.getName(), is("땡구르르"));
        assertThat(player.getBattleTag(), is("abc#12345"));
        assertThat(player.getProfilePlatforms(), notNullValue());
        assertThat(player.getProfilePlatforms().getPlatforms(), hasSize(5));
        assertThat(player.getPortraitImage(), notNullValue());

        LOGGER.debug("{}", player.getAchievements().getCategories());
        assertThat(player.getAchievements().get("Offense"), hasSize(12));

        LOGGER.debug("json: {}", player.toJSON());
    }

    @Test
    public void testFindPlayerException() throws IOException {
        Connection connection = Mockito.mock(Connection.class);
        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);
        Mockito.when(connection.get()).thenThrow(new HttpStatusException("404", 404, "url"));

        OverwatchClient client = new OverwatchClient.Builder().platform("pc").locale(new Locale("ko", "KR")).build();
        Optional<Player> optional = client.findPlayer("abc#12345");
        if (!optional.isPresent()) {
            assertThat(client.getException(), instanceOf(HttpStatusException.class));
        }
    }

    @Test
    public void testBuildURI() throws UnsupportedEncodingException {
        OverwatchClient client = new OverwatchClient.Builder().platform("pc").locale(new Locale("ko", "KR")).build();
        String uri = client.buildPlayerURL("abc#12345");
        assertThat(uri, is("https://playoverwatch.com/ko-kr/career/pc/kr/abc-12345"));
    }
}
