package com.company.controllers;

import com.company.Main;
import com.company.creatures.Hero;
import com.company.creatures.Monster;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class HeroCreatureController implements Runnable {
    private Hero hero;

    public HeroCreatureController(Hero hero) {
        this.hero = hero;
    }

    public String[] moves = new String[] {"top", "bottom", "left", "right"};

    public void run() {
        Thread.currentThread().setName("hero");
        while (Main.hero != null && !Main.monsters.isEmpty()) {
            Monster enemy = Main.gameLogic.checkForFight();

            if (enemy != null) {
                Main.gameLogic.startFight(Main.hero, enemy);
            } else {
                String move = moves[ThreadLocalRandom.current().nextInt(moves.length)];
                switch (move) {
                    case "top":
                        hero.MoveTop();
                        break;
                    case "bottom":
                        hero.MoveBottom();
                        break;
                    case "left":
                        hero.MoveLeft();
                        break;
                    case "right":
                        hero.MoveRight();
                        break;
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
