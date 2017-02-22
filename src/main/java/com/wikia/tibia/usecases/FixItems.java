package com.wikia.tibia.usecases;

import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

/** */
// TODO do the same for items as for creatures
public class FixItems {

    private MediaWikiBot mediaWikiBot;

    public FixItems(MediaWikiBot mediaWikiBot) {
        this.mediaWikiBot = mediaWikiBot;
    }
}