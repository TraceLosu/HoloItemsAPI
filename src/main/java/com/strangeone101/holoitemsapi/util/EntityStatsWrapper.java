package com.strangeone101.holoitemsapi.util;

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
    public boolean checkPlayer(Player player) {
        return player.getStatistic(getStatistic(), getSpecifier()) >= getGoal();
    }
}
