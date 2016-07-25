# overwatch-client 
playoverwatch.com client for java 

## How to use

```java
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

```

## Requirements 
* JDK8+
