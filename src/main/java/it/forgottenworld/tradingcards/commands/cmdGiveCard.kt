package it.forgottenworld.tradingcards.commands

import it.forgottenworld.tradingcards.card.CardManager
import it.forgottenworld.tradingcards.util.tcMsg
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

fun cmdGiveCard(p: Player, args: Array<String>, cardsConfig: FileConfiguration, messagesConfig: FileConfiguration): Boolean {

    if (!p.hasPermission("fwtc.givecard")) {
        tcMsg(p, "${messagesConfig.getString("Messages.NoPerms")}")
        return true
    }

    if (args.size <= 2) {
        tcMsg(p, "${messagesConfig.getString("Messages.GiveCardUsage")}")
        return true
    }

    if (cardsConfig.contains("Cards.${args[1].replace("_", " ")}.${args[2]}"))
        p.inventory.addItem(CardManager.getCard(args[2], args[1].replace("_", " "), 1))
    else
        tcMsg(p, "${messagesConfig.getString("Messages.NoCard")}")

    return true
}