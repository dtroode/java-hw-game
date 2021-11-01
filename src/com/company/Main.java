package com.company;

import com.company.creatures.Hero;
import com.company.creatures.Monster;
import com.company.equipment.weapons.Weapon;
import com.company.game.GameCycle;
import com.company.game.GameLogic;
import com.company.game.Map;
import com.company.game.MapDrawer;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static int polygonSize = 5;
    public static Random rnd = new Random();
    public static ArrayList<Weapon> weapons = new ArrayList<>();
    public static ArrayList<Monster> monsters = new ArrayList<>();
    public static Hero hero;

    public static char[][] polygon = new char[polygonSize][polygonSize];

    public static ExecutorService executor = Executors.newCachedThreadPool();
    public static Map map = new Map();
    public static GameCycle gameCycle = new GameCycle();
    public static MapDrawer mapDrawer = new MapDrawer();

    public static GameLogic gameLogic = new GameLogic();

    public static void main(String[] args) {
        map.prepare();
        GameLogic.generateWeapons();
        GameLogic.generateHero();
        GameLogic.generateMonsters();
        executor.execute(mapDrawer);
        executor.execute(gameCycle);
        executor.shutdown();
    }
}
