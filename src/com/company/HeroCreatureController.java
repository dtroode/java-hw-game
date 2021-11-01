package com.company;

import com.company.creatures.Creature;
import com.company.creatures.Monster;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class HeroCreatureController implements Runnable {
    private Creature hero;

    public HeroCreatureController(Creature hero) {
        this.hero = hero;
    }

    public String[] moves = new String[] {"top", "bottom", "left", "right"};

    public void run() {
        Thread.currentThread().setName("hero");
        while (Main.hero != null && !Main.monsters.isEmpty()) {
            Monster enemy = Main.GameLogic.checkForFight();

            if (enemy != null) {
                System.out.println(enemy.getName());
                Main.GameLogic.startFight(Main.hero, enemy);
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
