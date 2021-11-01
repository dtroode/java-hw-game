package com.company;

public abstract class Base implements Printable {
    static protected String resetColor = "\u001b[38;5;0m";
    protected String type;
    protected String title;
    protected String color = null;

    public String getType() { return type; }
}
