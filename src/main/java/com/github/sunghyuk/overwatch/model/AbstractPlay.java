package com.github.sunghyuk.overwatch.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonSubTypes.*;

/**
 * Created by sunghyuk on 2016. 7. 20..
 */
public abstract class AbstractPlay {

    private PlayStats featuredStats;

    private TopHeroes topHeroes;

    private CareerStats careerStats;

    public AbstractPlay(PlayStats featuredStats, TopHeroes topHeroes, CareerStats careerStats) {
        this.featuredStats = featuredStats;
        this.topHeroes = topHeroes;
        this.careerStats = careerStats;
    }

    public PlayStats getFeaturedStats() {
        return featuredStats;
    }

    public TopHeroes getTopHeroes() {
        return topHeroes;
    }

    public CareerStats getCareerStats() {
        return careerStats;
    }
}
