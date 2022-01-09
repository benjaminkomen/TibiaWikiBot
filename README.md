![Build Status](https://github.com/benjaminkomen/TibiaWikiBot/workflows/Build/badge.svg)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.onlaika%3ATibiaWikiBot&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.onlaika%3ATibiaWikiBot)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.onlaika%3ATibiaWikiBot&metric=coverage)](https://sonarcloud.io/dashboard?id=com.onlaika%3ATibiaWikiBot)

# TibiaWikiBot

## Purpose

TibiaWikiBot acts as a MediaWiki bot which can be run manually from your local machine. It has these main jobs:

- Creatures Service: check all creature pages for new loot information and add to item's droppedBy lists;
- Items Service: check all item pages for new loot information and add to creature's loot lists;
- Loot Statistics Service: check all loot statistics pages for new loot information and add to creature's loot lists.

The result of these services is used to add missing links to creatures on item pages and missing links to items on creature pages on [TibiaWiki](https://tibia.fandom.com), a wiki about the game [Tibia](https://www.tibia.com).

## See also

- [TibiaWiki Bot Proposal](http://tibia.fandom.com/wiki/User:Bennie/Bot_Proposal) for more information about the start of this project.
- [TibiaWikiApi](https://github.com/benjaminkomen/TibiaWikiApi) which is used to communicate with the wiki, using [jwiki](https://github.com/benjaminkomen/jwiki) to interact with the MediaWiki API.
