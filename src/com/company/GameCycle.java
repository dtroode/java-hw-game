package com.company;

import com.company.creatures.Monster;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameCycle implements Runnable {
    public static ExecutorService cycleExecutor = Executors.newCachedThreadPool();

    public void run() {
        cycleExecutor.execute(new HeroCreatureController(Main.hero));
        for (Monster monster : Main.monsters) {
            cycleExecutor.execute(new MonsterCreatureController(monster));
        }

        cycleExecutor.shutdown();
    }
}
