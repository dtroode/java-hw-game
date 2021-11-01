package com.company;

import com.company.elements.Fire;
import com.company.creatures.Hero;
import com.company.creatures.Monster;
import com.company.creatures.Creature;
import com.company.equipment.Spell;
import com.company.equipment.armour.*;
import com.company.equipment.weapons.Weapon;
import com.company.equipment.weapons.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static int polygonSize = 5;
    static Random rnd = new Random();
    public static ArrayList<Weapon> weapons = new ArrayList<>();
    public static ArrayList<Monster> monsters = new ArrayList<>();
    public static Hero hero;

    public static char[][] polygon = new char[polygonSize][polygonSize];

    public static ExecutorService executor = Executors.newCachedThreadPool();
    public static Map map = new Map();
    public static GameCycle gameCycle = new GameCycle();
    public static MapDrawer mapDrawer = new MapDrawer();

    public static void main(String[] args) {
        map.prepare();
        GameLogic.generateWeapons();
        GameLogic.generateHero();
        GameLogic.generateMonsters();
        executor.execute(mapDrawer);
        executor.execute(gameCycle);
        executor.shutdown();
    }

    public static class GameLogic {
        private static final String[] names = new String[] {"Ваня", "Саня", "Ивтихий", "Арсений", "Роберто", "Льюис"};
        private static final String[] monstertypes = new String[] {"Орк", "Зомби", "Собака", "Либерал", "Инцел"};
        private static final String[] herotypes = new String[] {"Маг", "Грибник"};
        private static final String[] weapontypes = new String[] {"ПП", "Пулемёт"};
        private static final String[] spelltypes = new String[] {};

        private static final int monstersNumber = 3;
        private static final int weaponsNumber = monstersNumber + 1;

        public static void generateWeapons() {
            for (int i = 0; i < weaponsNumber; i++) {
                String weapontype = weapontypes[ThreadLocalRandom.current().nextInt(weapontypes.length)];
                switch (weapontype) {
                    case "ПП":
                        weapons.add(new PP());
                        break;
                    case "Пулемёт":
                        weapons.add(new MachineGun());
                        break;
                }
            }
        }
        public static void generateHero() {
            if (weapons.isEmpty()) generateWeapons();

            String heroname = names[ThreadLocalRandom.current().nextInt(names.length)];
            String herotype = herotypes[ThreadLocalRandom.current().nextInt(herotypes.length)];
            int weaponnumber = ThreadLocalRandom.current().nextInt(weapons.size());

            Hero<Spell<Fire>, Spell<Fire>, Spell<Fire>> freshHero = new Hero<>(herotype, heroname,
                    ThreadLocalRandom.current().nextInt(50, 200),
                    0, 1, 0,
                    ThreadLocalRandom.current().nextInt(15, 50),
                    weapons.get(weaponnumber),
                    new GrayShield());

            weapons.remove(weaponnumber);
            hero = freshHero;
        }
        public static void generateMonsters() {
            if (weapons.isEmpty()) generateWeapons();
            for (int i = 0; i < monstersNumber; i++) {
                String monstername = names[ThreadLocalRandom.current().nextInt(names.length)];
                String monstertype = monstertypes[ThreadLocalRandom.current().nextInt(herotypes.length)];
                int weaponnumber = ThreadLocalRandom.current().nextInt(weapons.size());

                Monster freshMonster = new Monster(monstertype, monstername,
                        ThreadLocalRandom.current().nextInt(50, 100),
                        0,
                        weapons.get(weaponnumber),
                        new GrayShield());

                weapons.remove(weaponnumber);
                monsters.add(freshMonster);
            }
        }

        public static synchronized Monster checkForFight() {
            int heroX = hero.getX();
            int heroY = hero.getY();
            for (int count = 0; count < monsters.size(); count++) {
                if (heroX == monsters.get(count).getX() && heroY == monsters.get(count).getY()) {
                    return(monsters.get(count));
                }
            }
            return null;
        }

        public static void startFight(Creature c1, Creature c2) {
            int i = ThreadLocalRandom.current().nextInt(0, 1);
            if (i == 0) attackWithWeapon(c1, c2);
            else attackWithWeapon(c2, c1);
        }

        public static boolean checkIfWeaponBetter(Weapon w1, Weapon w2) {
            if (w1.getDamage() > w2.getDamage()) {
                return true;
            }
            return false;
        }

        public static boolean checkIfArmourBetter(Armour a1, Armour a2) {
            if (a1.getHealth() > a2.getHealth()) {
                return true;
            }
            return false;
        }

        public static int countDamage(Creature creature) {
            boolean didHit = rnd.nextBoolean();

            if (!didHit) return 0;
            if (creature.getWeapon() != null) {
                return (creature.getAttack() + creature.getWeapon().getDamage());
            } else {
                return creature.getAttack();
            }
        }
        public static void attackWithWeapon(Creature p1, Creature p2) {
            attack(p1, p2, p1.getWeapon().getDamage());
        }
        public static void attackWithSpell(Hero p1, Creature p2, int number) {
            switch (number) {
                case 1:
                    attack(p1, p2, p1.firstSpellDamage());
                    break;
                case 2:
                    attack(p1, p2, p1.secondSpellDamage());
                    break;
                case 3:
                    attack(p1, p2, p1.thirdSpellDamage());
                    break;
            }
        }
        private static synchronized void attack(Creature p1, Creature p2, int damage) {
            int attackerDamage = damage;

            if (attackerDamage == 0) {
                System.out.println("Не попал");
                attackWithWeapon(p2, p1);
                return;
            }

            String attackerName = p1.getName();
            String defenderName = p2.getName();

            System.out.println(attackerName + " аттаковал " + defenderName + " на " + attackerDamage + " урона.");

            int defenderArmour = p2.getArmour().getHealth();
            int defenderHealth = p2.getHealth();

            if (attackerDamage > defenderArmour) {
                p2.getArmour().setHealth(0);
                if (attackerDamage < (defenderHealth + defenderArmour)) {
                    p2.setHealth(defenderHealth - (attackerDamage - defenderArmour));
                } else {
                    p2.setHealth(0);
                    if (p2.getType().equals("Персонаж")) {
                        System.out.println("ВЫ ПРОИГРАЛИ");
                        hero = null;
                    } else {
                        Thread enemyThread = null;
                        for (Thread t : Thread.getAllStackTraces().keySet()) {
                            if (t.getName().equals("monster-" + p2.getId())) enemyThread = t;
                        }
                        monsters.remove(p2);
                        enemyThread.interrupt();

                        // Заменяем оружие героя на оружие моба, если у моба оружие лучше
                        if (checkIfWeaponBetter(p2.getWeapon(), p1.getWeapon())) {
                            p1.setWeapon(p2.getWeapon());
                            System.out.println("У поверженного моба оружие было лучше, герой берёт его");
                        }

                        // Заменяем щит героя на щит моба, если у моба щит лучше
                        if (checkIfArmourBetter(p2.getArmour(), p1.getArmour())) {
                            p1.setArmour(p2.getArmour());
                            System.out.println("У поверженного моба щит был лучше, герой берёт его");
                        }

                        System.out.println("Вы убили " + defenderName);
                        System.out.println("У вас осталось: " + p1.getArmour().getHealth() + " единиц щита и " + p1.getHealth() + " единиц здоровья.");
                    }
                    return;
                }
            } else {
                p2.getArmour().setHealth(defenderArmour - attackerDamage);
            }

            System.out.println("У " + defenderName + " осталось: " + p2.getArmour().getHealth() + " единиц щита и " + p2.getHealth() + " единиц здоровья.");
            attackWithWeapon(p2, p1);
        }
    }
}
