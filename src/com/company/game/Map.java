package com.company.game;

import com.company.Main;

public class Map {
    public void prepare() {
        for (int i = 0; i < Main.polygonSize; i++) {
            for (int j = 0; j < Main.polygonSize; j++) {
                Main.polygon[i][j] = '_';
            }
        }
    }
}
