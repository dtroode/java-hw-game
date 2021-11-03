package com.company.game;

import com.company.Main;
import com.company.creatures.Monster;

import java.util.concurrent.TimeUnit;

public class MapDrawer implements Runnable {
    public synchronized void run() {
        while (Main.hero != null && !Main.monsters.isEmpty()) {
            // Подготовка карты (очистка всех клеток)
            Main.map.prepare();

            // Отрисовка гг
            Main.polygon[Main.hero.getY()][Main.hero.getX()] = '@';
            // Отрисовка монстров
            for (Monster monster : Main.monsters) {
                Main.polygon[monster.getY()][monster.getX()] = monster.getName().charAt(0);
            }

            // Вывод карты
            for (int i = 0; i < Main.polygonSize; i++) {
                for (int j = 0; j < Main.polygonSize; j++) {
                    System.out.print(Main.polygon[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {}
        }
    }
}
