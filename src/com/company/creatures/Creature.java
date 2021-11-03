package com.company.creatures;

import com.company.base.Base;
import com.company.Main;
import com.company.equipment.armour.Armour;
import com.company.equipment.weapons.Weapon;

import java.util.Random;

// Класс персоны. Имеет: имя, здоровье, показатели атаки и защиты
public abstract class Creature extends Base {
    Random rnd = new Random();
    public static int number = 1;
    protected int id;
    // Координаты
    protected int x;
    protected int y;

    public Creature() {
        synchronized (this) {
            id = number;
            number++;
        }
    }

    // Поля класса персоны
    protected String name;
    protected int health;
    protected int attack;
    protected Weapon weapon;
    protected Armour armour;

    // Методы для передвижения
    public synchronized void MoveTop() {
        if (y != 0) {
            y -= 1;
        } else {
            y = Main.polygonSize - 1;
        }
    }

    public synchronized void MoveBottom() {
        if (y != Main.polygonSize - 1) {
            y += 1;
        } else {
            y = 0;
        }
    }

    public synchronized void MoveLeft() {
        if (x != 0) {
            x -= 1;
        } else {
            x = Main.polygonSize - 1;
        }
    }

    public synchronized void MoveRight() {
        if (x != Main.polygonSize - 1) {
            x += 1;
        } else {
            x = 0;
        }
    }

    // Методы, дающие доступ
    public String getName() { return name; }
    public synchronized int getHealth() { return health; }
    public synchronized void setHealth(int health) { this.health = health; }
    public synchronized int getAttack() { return attack; }
    public synchronized Weapon getWeapon() { return weapon; }
    public abstract void setWeapon(Weapon weapon);
    public synchronized Armour getArmour() { return armour; }
    public abstract void setArmour(Armour armour);
    public synchronized int getId() { return this.id; }


    public synchronized int getX() { return x; }
    public synchronized int getY() { return y; }

    public synchronized void setX(int x) { this.x = x; }
    public synchronized void setY(int y) { this.y = y; }
}
