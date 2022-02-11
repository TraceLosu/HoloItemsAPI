package com.strangeone101.holoitemsapi.util;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class StatsWrapper<T> {

    private final Statistic statistic;
    private final int goal;
    private final T specifier;

    public StatsWrapper(Statistic statistic, T specifier, int goal){
        this.statistic = statistic;
        this.specifier = specifier;
        this.goal = goal;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public int getGoal() {
        return goal;
    }

    public T getSpecifier() {
        return specifier;
    }

    public abstract boolean checkPlayer(OfflinePlayer player);

    public abstract int inspectPlayer(OfflinePlayer player);
}
