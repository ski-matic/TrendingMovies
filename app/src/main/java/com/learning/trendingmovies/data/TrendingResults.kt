package com.learning.trendingmovies.data

data class TrendingResults(
    var page: Int,
    var results: List<Movie>,
    var total_pages: Int,
    var total_results: Int
) {
    override fun toString(): String {
        return "TrendingResults(page=$page, results=${results.count()}, total_pages=$total_pages, total_results=$total_results)"
    }
}

/*
{
  "page": 1,
  "results": [
    {
      "id": 499701,
      "video": false,
      "vote_count": 215,
      "vote_average": 6.4,
      "title": "Dora and the Lost City of Gold",
      "release_date": "2019-08-08",
      "original_language": "en",
      "original_title": "Dora and the Lost City of Gold",
      "genre_ids": [
        12,
        35,
        10751
      ],
      "backdrop_path": "/61sbyO47yIpsMFyLhY1MWcqjg1Q.jpg",
      "adult": false,
      "overview": "Dora, a girl who has spent most of her life exploring the jungle with her parents, now must navigate her most dangerous adventure yet: high school. Always the explorer, Dora quickly finds herself leading Boots (her best friend, a monkey), Diego, and a rag tag group of teens on an adventure to save her parents and solve the impossible mystery behind a lost Inca civilization.",
      "poster_path": "/xvYCZ740XvngXK0FNeSNVTJJJ5v.jpg",
      "popularity": 70.255,
      "media_type": "movie"
    },
    {
      "id": 420818,
      "video": false,
      "vote_count": 3419,
      "vote_average": 7.1,
      "title": "The Lion King",
      "release_date": "2019-07-12",
      "original_language": "en",
      "original_title": "The Lion King",
      "genre_ids": [
        12,
        16,
        18
      ],
      "backdrop_path": "/nRXO2SnOA75OsWhNhXstHB8ZmI3.jpg",
      "adult": false,
      "overview": "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
      "poster_path": "/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
      "popularity": 176.156,
      "media_type": "movie"
    },
    {
      "id": 295151,
      "video": false,
      "vote_count": 23,
      "vote_average": 6.4,
      "title": "Let It Snow",
      "release_date": "2019-11-08",
      "original_language": "en",
      "original_title": "Let It Snow",
      "genre_ids": [
        35,
        10749
      ],
      "backdrop_path": "/9REB0BCTk2RueTj5PuELYRYJN5e.jpg",
      "adult": false,
      "overview": "When a huge blizzard (that doesn't show signs of stopping) hits, Gracetown is completely snowed in. But even though it's cold outside, things are heating up inside, proving that Christmas is magical when it comes to love.",
      "poster_path": "/tXTccijjTnpXWFEMaHC1gp59cNc.jpg",
      "popularity": 44.423,
      "media_type": "movie"
    },
    {
      "id": 384018,
      "video": false,
      "vote_count": 1784,
      "vote_average": 6.5,
      "title": "Fast & Furious Presents: Hobbs & Shaw",
      "release_date": "2019-08-01",
      "original_language": "en",
      "original_title": "Fast & Furious Presents: Hobbs & Shaw",
      "genre_ids": [
        28,
        12,
        35
      ],
      "backdrop_path": "/qAhedRxRYWZAgZ8O8pHIl6QHdD7.jpg",
      "adult": false,
      "overview": "Ever since US Diplomatic Security Service Agent Hobbs and lawless outcast Shaw first faced off, they just have swapped smacks and bad words. But when cyber-genetically enhanced anarchist Brixton's ruthless actions threaten the future of humanity, both join forces to defeat him. (A spin-off of “The Fate of the Furious,” focusing on Johnson's Luke Hobbs and Statham's Deckard Shaw.)",
      "poster_path": "/kvpNZAQow5es1tSY6XW2jAZuPPG.jpg",
      "popularity": 145.2,
      "media_type": "movie"
    },
    {
      "id": 504949,
      "video": false,
      "vote_count": 327,
      "vote_average": 7.3,
      "title": "The King",
      "release_date": "2019-10-11",
      "original_language": "en",
      "original_title": "The King",
      "genre_ids": [
        18,
        36,
        10752
      ],
      "backdrop_path": "/oMAhce30UvkgJwlzMwsuLaPJ5cG.jpg",
      "adult": false,
      "overview": "England, 15th century. Hal, a capricious prince who lives among the populace far from court, is forced by circumstances to reluctantly accept the throne and become Henry V.",
      "poster_path": "/8u0QBGUbZcBW59VEAdmeFl9g98N.jpg",
      "popularity": 96.173,
      "media_type": "movie"
    },
    {
      "id": 500916,
      "video": false,
      "vote_count": 2,
      "vote_average": 8,
      "title": "Primal",
      "release_date": "2019-11-08",
      "original_language": "en",
      "original_title": "Primal",
      "genre_ids": [
        28
      ],
      "backdrop_path": "/54Lhj5EAH0VEnObCqUSCHkvhGQl.jpg",
      "adult": false,
      "overview": "A big-game hunter for zoos who has booked passage on a Greek shipping freighter with a fresh haul of exotic and deadly animals from the Amazon, including a rare white Jaguar - along with a political assassin being extradited to the U.S in secret. Two days into the journey, the assassin escapes and releases the captive animals, throwing the ship into chaos.",
      "poster_path": "/v0Air5GTsfgtjsnZyji2lH6r2b8.jpg",
      "popularity": 45.653,
      "media_type": "movie"
    },
    {
      "id": 429617,
      "video": false,
      "vote_count": 4883,
      "vote_average": 7.6,
      "title": "Spider-Man: Far from Home",
      "release_date": "2019-06-28",
      "original_language": "en",
      "original_title": "Spider-Man: Far from Home",
      "genre_ids": [
        28,
        12,
        878
      ],
      "backdrop_path": "/5myQbDzw3l8K9yofUXRJ4UTVgam.jpg",
      "adult": false,
      "overview": "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
      "poster_path": "/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
      "popularity": 103.306,
      "media_type": "movie"
    },
    {
      "id": 571650,
      "video": false,
      "vote_count": 11,
      "vote_average": 4.3,
      "title": "Paradise Beach",
      "release_date": "2019-02-20",
      "original_language": "fr",
      "original_title": "Paradise Beach",
      "genre_ids": [
        28,
        80,
        53
      ],
      "backdrop_path": "/ljaOTj4HqM5XEPoNVEmrJzJ60Ux.jpg",
      "adult": false,
      "overview": "A team of former robbers arrived at Paradise: Phuket, southern Thailand. Now traders, they are happy days. Until the day when the devil arrives: Mehdi, sentenced to 15 years in prison during the robbery, comes to recover his share of the cake.",
      "poster_path": "/oFidx1CYA0mHERbnY26B8l42aBa.jpg",
      "popularity": 4.82,
      "media_type": "movie"
    },
    {
      "id": 475557,
      "video": false,
      "vote_count": 4983,
      "vote_average": 8.5,
      "title": "Joker",
      "release_date": "2019-10-02",
      "original_language": "en",
      "original_title": "Joker",
      "genre_ids": [
        80,
        18,
        53
      ],
      "backdrop_path": "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
      "adult": false,
      "overview": "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
      "poster_path": "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
      "popularity": 394.256,
      "media_type": "movie"
    },
    {
      "id": 299534,
      "video": false,
      "vote_count": 10128,
      "vote_average": 8.3,
      "title": "Avengers: Endgame",
      "release_date": "2019-04-24",
      "original_language": "en",
      "original_title": "Avengers: Endgame",
      "genre_ids": [
        28,
        12,
        878
      ],
      "backdrop_path": "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
      "adult": false,
      "overview": "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
      "poster_path": "/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
      "popularity": 52.61,
      "media_type": "movie"
    },
    {
      "id": 501170,
      "video": false,
      "vote_count": 202,
      "vote_average": 7.1,
      "title": "Doctor Sleep",
      "release_date": "2019-10-30",
      "original_language": "en",
      "original_title": "Doctor Sleep",
      "genre_ids": [
        27
      ],
      "backdrop_path": "/1nm0sk8UUx9jHCTHuMKe2jkt4Pn.jpg",
      "adult": false,
      "overview": "A traumatized, alcoholic Dan Torrance meets Abra, a kid who also has the ability to \"shine.\" He tries to protect her from the True Knot, a cult whose goal is to feed off of people like them in order to remain immortal.",
      "poster_path": "/p69QzIBbN06aTYqRRiCOY1emNBh.jpg",
      "popularity": 124.581,
      "media_type": "movie"
    },
    {
      "id": 301528,
      "video": false,
      "vote_count": 2939,
      "vote_average": 7.6,
      "title": "Toy Story 4",
      "release_date": "2019-06-19",
      "original_language": "en",
      "original_title": "Toy Story 4",
      "genre_ids": [
        12,
        16,
        35,
        14,
        10751
      ],
      "backdrop_path": "/m67smI1IIMmYzCl9axvKNULVKLr.jpg",
      "adult": false,
      "overview": "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
      "poster_path": "/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
      "popularity": 65.267,
      "media_type": "movie"
    },
    {
      "id": 393624,
      "video": false,
      "vote_count": 25,
      "vote_average": 7.1,
      "title": "Official Secrets",
      "release_date": "2019-08-30",
      "original_language": "en",
      "original_title": "Official Secrets",
      "genre_ids": [
        18,
        53,
        10749,
        10752
      ],
      "backdrop_path": "/JPLqPszdIZS3uknr7oG6qJyyKV.jpg",
      "adult": false,
      "overview": "The true story of British intelligence whistleblower Katharine Gun who—prior to the 2003 Iraq invasion—leaked a top-secret NSA memo exposing a joint US-UK illegal spying operation against members of the UN Security Council. The memo proposed blackmailing member states into voting for war.",
      "poster_path": "/lyCGqSkT3PqLYQXiWs4FCVJBAYW.jpg",
      "popularity": 22.887,
      "media_type": "movie"
    },
    {
      "id": 515195,
      "video": false,
      "vote_count": 934,
      "vote_average": 6.7,
      "title": "Yesterday",
      "release_date": "2019-06-27",
      "original_language": "en",
      "original_title": "Yesterday",
      "genre_ids": [
        35,
        14,
        10402,
        10749
      ],
      "backdrop_path": "/rLBkg55NUSwFjPjVYHOXyQc0hAL.jpg",
      "adult": false,
      "overview": "Jack Malik is a struggling singer-songwriter in an English seaside town whose dreams of fame are rapidly fading, despite the fierce devotion and support of his childhood best friend, Ellie. After a freak bus accident during a mysterious global blackout, Jack wakes up to discover that he's the only person on Earth who can remember The Beatles.",
      "poster_path": "/1rjaRIAqFPQNnMtqSMLtg0VEABi.jpg",
      "popularity": 55.766,
      "media_type": "movie"
    },
    {
      "id": 508664,
      "video": false,
      "vote_count": 2,
      "vote_average": 7.5,
      "title": "Danger Close: The Battle of Long Tan",
      "release_date": "2019-08-08",
      "original_language": "en",
      "original_title": "Danger Close: The Battle of Long Tan",
      "genre_ids": [
        28,
        18,
        36,
        10752
      ],
      "backdrop_path": "/zA6uqJdLMfqg0srHccAK8aLTHJE.jpg",
      "adult": false,
      "overview": "For three and a half hours, in the pouring rain, amid the mud and shattered trees of a rubber plantation called Long Tan, Major Harry Smith and his dispersed company of 108 young and mostly inexperienced Australian and New Zealand soldiers are fighting for their lives, holding off an overwhelming enemy force of 2,500 battle hardened Viet Cong and North Vietnamese soldiers. With their ammunition running out, their casualties mounting and the enemy massing for a final assault, each man begins to search for the strength to triumph over an uncertain future with honour, decency and courage.",
      "poster_path": "/aB1rCWGMsM2mm1kBDleaPkqUCFo.jpg",
      "popularity": 26.828,
      "media_type": "movie"
    },
    {
      "id": 527261,
      "video": false,
      "vote_count": 406,
      "vote_average": 5.7,
      "title": "The Silence",
      "release_date": "2019-05-16",
      "original_language": "en",
      "original_title": "The Silence",
      "genre_ids": [
        18,
        14,
        53
      ],
      "backdrop_path": "/iHJQIKSLHN2mUUWySpj2MO5HVtS.jpg",
      "adult": false,
      "overview": "With the world under attack by deadly creatures who hunt by sound, a teen and her family seek refuge outside the city and encounter a mysterious cult.",
      "poster_path": "/lTVOquzxw2DPF3MKuYd1ynz9F6H.jpg",
      "popularity": 33.796,
      "media_type": "movie"
    },
    {
      "id": 521777,
      "video": false,
      "vote_count": 342,
      "vote_average": 6.6,
      "title": "Good Boys",
      "release_date": "2019-08-14",
      "original_language": "en",
      "original_title": "Good Boys",
      "genre_ids": [
        35
      ],
      "backdrop_path": "/zIZv4pPJRg3YTnWWuotwXnYu1fM.jpg",
      "adult": false,
      "overview": "A group of young boys on the cusp of becoming teenagers embark on an epic quest to fix their broken drone before their parents get home.",
      "poster_path": "/tximyCXMEnWIIyOy9STkOduUprG.jpg",
      "popularity": 81.801,
      "media_type": "movie"
    },
    {
      "id": 463257,
      "video": false,
      "vote_count": 52,
      "vote_average": 7.5,
      "title": "The Peanut Butter Falcon",
      "release_date": "2019-08-09",
      "original_language": "en",
      "original_title": "The Peanut Butter Falcon",
      "genre_ids": [
        12,
        35,
        18
      ],
      "backdrop_path": "/4NJ9Wbib4YfvnZyyvV8WSLOAQUh.jpg",
      "adult": false,
      "overview": "A down-on-his-luck crab fisherman embarks on a journey to get a young man with Down syndrome to a professional wrestling school in rural North Carolina and away from the retirement home where he’s lived for the past two and a half years.",
      "poster_path": "/qyQcRGvdW3VtxHR4fSDgPOePEip.jpg",
      "popularity": 44.306,
      "media_type": "movie"
    },
    {
      "id": 412117,
      "video": false,
      "vote_count": 789,
      "vote_average": 6.6,
      "title": "The Secret Life of Pets 2",
      "release_date": "2019-05-24",
      "original_language": "en",
      "original_title": "The Secret Life of Pets 2",
      "genre_ids": [
        12,
        16,
        35,
        10751
      ],
      "backdrop_path": "/etaok7q2E5tV36oXe7GQzhUQ4fX.jpg",
      "adult": false,
      "overview": "Max the terrier must cope with some major life changes when his owner gets married and has a baby. When the family takes a trip to the countryside, nervous Max has numerous run-ins with canine-intolerant cows, hostile foxes and a scary turkey. Luckily for Max, he soon catches a break when he meets Rooster, a gruff farm dog who tries to cure the lovable pooch of his neuroses.",
      "poster_path": "/nYcaCNkB4EgVyvrXxxbklefDrGL.jpg",
      "popularity": 43.939,
      "media_type": "movie"
    },
    {
      "id": 290859,
      "video": false,
      "vote_count": 408,
      "vote_average": 6.6,
      "title": "Terminator: Dark Fate",
      "release_date": "2019-10-23",
      "original_language": "en",
      "original_title": "Terminator: Dark Fate",
      "genre_ids": [
        28,
        878
      ],
      "backdrop_path": "/rtf4vjjLZLalpOzDUi0Qd2GTUqq.jpg",
      "adult": false,
      "overview": "More than two decades have passed since Sarah Connor prevented Judgment Day, changed the future, and re-wrote the fate of the human race. Dani Ramos is living a simple life in Mexico City with her brother and father when a highly advanced and deadly new Terminator – a Rev-9 – travels back through time to hunt and kill her. Dani's survival depends on her joining forces with two warriors: Grace, an enhanced super-soldier from the future, and a battle-hardened Sarah Connor. As the Rev-9 ruthlessly destroys everything and everyone in its path on the hunt for Dani, the three are led to a T-800 from Sarah’s past that may be their last best hope.",
      "poster_path": "/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg",
      "popularity": 282.827,
      "media_type": "movie"
    }
  ],
  "total_pages": 1000,
  "total_results": 20000
}
 */


