package com.github.sunghyuk.overwatch;

import com.github.sunghyuk.overwatch.model.Player;
import com.github.sunghyuk.overwatch.page.CareerPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Optional;

/**
 * Created by sunghyuk on 2016. 7. 20..
 */
public class OverwatchClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverwatchClient.class);

    private static final String PLAY_OVERWATCH_DOT_COM = "https://playoverwatch.com";

    private final String platform;
    private final String region;
    private final Locale locale;

    private Exception exception;

    private final OverwatchHtmlParser parser;

    private OverwatchClient(Builder builder) {
        this.platform = builder.platform;
        this.region = builder.region;
        this.locale = builder.locale;

        this.parser = new OverwatchHtmlParser();
    }

    public String getPlatform() {
        return platform;
    }

    public String getRegion() {
        return region;
    }

    public Locale getLocale() {
        return locale;
    }

    public Exception getException() {
        return exception;
    }

    protected String buildPlayerURL(String battleTag) throws UnsupportedEncodingException {
        MessageFormat uriFormat = new MessageFormat("{0}/{1}-{2}/career/{3}/{4}/{5}");
        return uriFormat.format(new Object[]{
                PLAY_OVERWATCH_DOT_COM,
                locale.getLanguage().toLowerCase(),
                locale.getCountry().toLowerCase(),
                platform,
                region,
                URLEncoder.encode(battleTag.replace("#", "-"), "utf-8")
        });
    }

    protected Document getDocument(String uri) throws IOException {
        return Jsoup.connect(uri).get();
    }

    public Optional<Player> findPlayer(String battleTag) {
        CareerPage page = null;
        try {
            String url = buildPlayerURL(battleTag);
            Document document = getDocument(url);
            page = parser.parseCareerPage(document);
        } catch (IOException e) {
            LOGGER.error("jsoup error", e);
            this.exception = e;
        }

        if (page == null) {
            return Optional.empty();
        }
        return Optional.of(new Player(battleTag, page));
    }

    public static class Builder {
        private String platform;
        private String region;
        private Locale locale;

        public Builder platform(String platform) {
            this.platform = platform;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder locale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public OverwatchClient build() {
            return new OverwatchClient(this);
        }
    }

}
