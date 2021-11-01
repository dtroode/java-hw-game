package com.company;

import com.company.creatures.Creature;
import com.company.creatures.Monster;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MonsterCreatureController implements Runnable {
    private Monster monster;

    public MonsterCreatureController(Monster monster) {
        this.monster = monster;
    }

    public String[] moves = new String[] {"top", "bottom", "left", "right"};

    public void run() {
        while (Main.hero != null && !Main.monsters.isEmpty()) {
            Thread.currentThread().setName("monster-" + monster.getId());
            String move = moves[ThreadLocalRandom.current().nextInt(moves.length)];
            switch (move) {
                case "top":
                    monster.MoveTop();
                    break;
                case "bottom":
                    monster.MoveBottom();
                    break;
                case "left":
                    monster.MoveLeft();
                    break;
                case "right":
                    monster.MoveRight();
                    break;
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
            }
        }
    }
}
