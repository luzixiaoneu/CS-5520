package com.example.cs_5520_a1.entity;

public class LinkElement {
    String name;
    String URL;

    public LinkElement(String name, String URL) {
        this.name = name;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }
}
