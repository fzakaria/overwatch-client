package com.github.sunghyuk.overwatch;

import com.github.sunghyuk.overwatch.model.*;

import java.util.List;
import java.util.Locale;

/**
 * Created by sunghyuk on 2016. 7. 25..
 */
public class Sample {

    public static void main(String[] args) {
        apiDemo();

        compare("땡구르르#3506", "Sean#3536");
    }

    private static void compare(String tag1, String tag2) {
        OverwatchClient client = new OverwatchClient.Builder().locale(new Locale("ko", "KR")).platform("pc").region("kr").build();

        Player player1 = client.findPlayer(tag1);
        Player player2 = client.findPlayer(tag2);

        System.out.println(String.format("%s VS %s", player1.getName(), player2.getName()));
        System.out.println("=== 주요 통계 ===");
        for (String key : player1.getQuickPlay().getFeaturedStats().keySet()) {
            System.out.println(String.format("[%s] %s : %s", key, player1.getQuickPlay().getFeaturedStats().get(key), player2.getQuickPlay().getFeaturedStats().get(key)));
        }
    }

    private static void apiDemo() {
        OverwatchClient client = new OverwatchClient.Builder()
                .platform("pc")
                .region("eu")
                .locale(new Locale("en", "US"))
                .build();


        // player info
        Player player = client.findPlayer("lllllllll#21759");
        String name = player.getName();
        int level = player.getLevel();
        ProfilePlatforms profilePlatforms = player.getProfilePlatforms();

        // all keys are localized string based on client locale
        // quick and competitive play stats
        QuickPlay quick = player.getQuickPlay();
        String eliminationsAverage = quick.getFeaturedStats().get("ELIMINATIONS - AVERAGE");
        HeroComparisonData heroComparisonData = quick.getTopHeroes().getData().get("WIN PERCENTAGE");

        // get overall stats
        PlayStats combat = quick.getCareerStats().getStats().get("ALL HEROES").get("COMBAT");

        // get hero specific stats
        List<String> heroes = quick.getCareerStats().getHeroes();
        PlayStats comabt = quick.getCareerStats().getStats().get("Widowmaker").get("COMBAT");
        PlayStats heroSpecific = quick.getCareerStats().getStats().get("Widowmaker").get("HERO SPECIFIC");

        // achievements
        List<String> categories = player.getAchievements().getCategories();
        List<Achievement> achievements = player.getAchievements().get("GENERAL");

        // return json
        String json = player.toJSON();
    }
}
