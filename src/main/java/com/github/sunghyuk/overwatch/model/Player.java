package com.github.sunghyuk.overwatch.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sunghyuk.overwatch.page.CareerPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by sunghyuk on 2016. 7. 19..
 */
public class Player {

    private String name;

    private String portraitImage;

    private String battleTag;

    private int level;

    private Rank rank;

    private ProfilePlatforms profilePlatforms;

    private QuickPlay quickPlay;

    private CompetitivePlay competitivePlay;

    private Achievements achievements;

    private String json;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

    public Player(String battleTag, CareerPage page) {
        this.battleTag = battleTag;
        this.name = page.getName();
        this.profilePlatforms = page.getPlatforms();
        this.portraitImage = page.getPortraitImage();
        this.level = page.getLevel();
        this.rank = page.getRank();
        this.quickPlay = new QuickPlay(page.getQuickFeaturedStats(), page.getQuickTopHeroes(), page.getQuickCareerStats());
        this.competitivePlay = new CompetitivePlay(page.getCompetitiveFeaturedStats(), page.getCompetitiveTopHeroes(), page.getCompetitiveCareerStats());
        this.achievements = page.getAchievements();
    }

    public String getName() {
        return name;
    }

    public String getPortraitImage() {
        return portraitImage;
    }

    public String getBattleTag() {
        return battleTag;
    }

    public ProfilePlatforms getProfilePlatforms() {
        return profilePlatforms;
    }

    public int getLevel() {
        return level;
    }

    public Rank getRank() {
        return rank;
    }

    public QuickPlay getQuickPlay() {
        return quickPlay;
    }

    public CompetitivePlay getCompetitivePlay() {
        return competitivePlay;
    }

    public Achievements getAchievements() {
        return achievements;
    }

    public String toJSON() {
        if (json == null) {
            try {
                this.json = MAPPER.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                LOGGER.error("", e);
            }
        }
        return json;
    }
}
