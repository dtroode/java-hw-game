package com.company.creatures;

//import com.company.tool.Armour;
import com.company.Main;
import com.company.equipment.armour.Armour;
import com.company.equipment.weapons.Weapon;

import java.util.concurrent.ThreadLocalRandom;

// Класс врага. Имеет: имя, здоровье, опыт, уровень, показатели атаки и защиты
// Наследуется от класса персоны и имплементирует интерфейс, определяющий вывод информации
public class Monster extends Creature {
    private int id;

    public Monster(String title, String name,
                   int health, int attack,
                   Weapon weapon, Armour armour) {
        this.type = "Монстр";
        this.title = title;
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.weapon = weapon;
        this.armour = armour;

        setX(ThreadLocalRandom.current().nextInt(Main.polygonSize-1));
        setY(ThreadLocalRandom.current().nextInt(Main.polygonSize-1));
    }

    public synchronized void setWeapon(Weapon weapon) { this.weapon = weapon; }
    public synchronized void setArmour(Armour armour) { this.armour = armour; }

    // Реализация метода из интерфейса, вывод всей информации
    public void printAllInf() {
        System.out.println( color +
                "Враг: " + title + ", " + this.getName() +
                resetColor + " | " +
                "Здоровье: " + this.getHealth() +
                ", атака: " + this.getAttack() + ".");
    }
}
