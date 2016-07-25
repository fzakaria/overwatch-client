package com.github.sunghyuk.overwatch.page;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.sunghyuk.overwatch.model.*;
import com.github.sunghyuk.overwatch.processor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sunghyuk on 2016. 7. 20..
 */
public final class CareerPage extends Page {

    private static final Logger LOGGER = LoggerFactory.getLogger(CareerPage.class);

    @Selector(value = "#overview-section > div > div.page-wrapper.row.content-box > div > div > div.masthead-player > h1", handler = ElementTextProcessor.class)
    private String name;

    @Selector(value = "#overview-section > div > div.page-wrapper.row.content-box > div > div > div.masthead-player > img", handler = ImageSrcProcessor.class)
    private String portraitImage;

    @Selector(value = "script[id=platform-btn-template] + script", handler = ProfilePlatformProcessor.class)
    private ProfilePlatforms platforms;

    @Selector(value = "#overview-section > div > div.page-wrapper.row.content-box > div > div > div.masthead-player-progression > div > div.player-level", handler = LevelProcessor.class)
    private int level;

    @Selector(value = "#overview-section > div > div.page-wrapper.row.content-box > div > div > div.masthead-player-progression > div > div.competitive-rank", handler = RankProcessor.class)
    private Rank rank;

    @Selector(value = "#quick-play > section.content-box.page-wrapper.highlights-section > div > ul", handler = FeaturedStatsProcessor.class)
    private PlayStats quickFeaturedStats;

    @Selector(value = "#competitive-play > section.content-box.page-wrapper.highlights-section > div > ul", handler = FeaturedStatsProcessor.class)
    private PlayStats competitiveFeaturedStats;

    @Selector(value = "#quick-play > section.content-box.page-wrapper.hero-comparison-section > div", handler = TopHeroesProcessor.class)
    private TopHeroes quickTopHeroes;

    @Selector(value = "#competitive-play > section.content-box.page-wrapper.hero-comparison-section > div", handler = TopHeroesProcessor.class)
    private TopHeroes competitiveTopHeroes;

    @Selector(value = "#quick-play > section.content-box.page-wrapper.career-stats-section", handler = CareerStatsProcessor.class)
    private CareerStats quickCareerStats;

    @Selector(value = "#competitive-play > section.content-box.page-wrapper.career-stats-section", handler = CareerStatsProcessor.class)
    private CareerStats competitiveCareerStats;

    @Selector(value = "#achievements-section", handler = AchievementsProcessor.class)
    private Achievements achievements;

    public Achievements getAchievements() {
        return achievements;
    }

    public void setAchievements(Achievements achievements) {
        this.achievements = achievements;
    }

    public CareerStats getCompetitiveCareerStats() {
        return competitiveCareerStats;
    }

    public void setCompetitiveCareerStats(CareerStats competitiveCareerStats) {
        this.competitiveCareerStats = competitiveCareerStats;
    }

    public CareerStats getQuickCareerStats() {
        return quickCareerStats;
    }

    public void setQuickCareerStats(CareerStats quickCareerStats) {
        this.quickCareerStats = quickCareerStats;
    }

    public TopHeroes getCompetitiveTopHeroes() {
        return competitiveTopHeroes;
    }

    public void setCompetitiveTopHeroes(TopHeroes competitiveTopHeroes) {
        this.competitiveTopHeroes = competitiveTopHeroes;
    }

    public TopHeroes getQuickTopHeroes() {
        return quickTopHeroes;
    }

    public void setQuickTopHeroes(TopHeroes quickTopHeroes) {
        this.quickTopHeroes = quickTopHeroes;
    }

    public PlayStats getCompetitiveFeaturedStats() {
        return competitiveFeaturedStats;
    }

    public void setCompetitiveFeaturedStats(PlayStats competitiveFeaturedStats) {
        this.competitiveFeaturedStats = competitiveFeaturedStats;
    }

    public PlayStats getQuickFeaturedStats() {
        return quickFeaturedStats;
    }

    public void setQuickFeaturedStats(PlayStats quickFeaturedStats) {
        this.quickFeaturedStats = quickFeaturedStats;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortraitImage() {
        return portraitImage;
    }

    public void setPortraitImage(String portraitImage) {
        this.portraitImage = portraitImage;
    }

    public ProfilePlatforms getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ProfilePlatforms platforms) {
        this.platforms = platforms;
    }

    @Override
    public String toJSON() {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOGGER.error("json processing error", e);
        }
        return null;
    }
}
