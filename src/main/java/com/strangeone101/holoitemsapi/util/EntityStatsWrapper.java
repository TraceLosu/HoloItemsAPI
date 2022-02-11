package com.strangeone101.holoitemsapi.util;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class EntityStatsWrapper extends StatsWrapper<EntityType>{

    public EntityStatsWrapper(Statistic statistic, EntityType specifier, int goal) {
        super(statistic, specifier, goal);

        if(statistic.getType() != Statistic.Type.ENTITY)
            throw new IllegalArgumentException("Statistic " + statistic + " doesn't use EntityType specifier.");
    }

    @Override
    public boolean checkPlayer(OfflinePlayer player) {
        return player.getStatistic(getStatistic(), getSpecifier()) >= getGoal();
    }

    @Override
    public int inspectPlayer(OfflinePlayer player) {
        return getGoal() - player.getStatistic(getStatistic(), getSpecifier());
    }
}
