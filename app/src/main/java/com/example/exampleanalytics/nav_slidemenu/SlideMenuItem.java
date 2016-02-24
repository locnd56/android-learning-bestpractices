package com.example.exampleanalytics.nav_slidemenu;

/**
 * Created by Mr.Incredible on 2/20/2016.
 */
public class SlideMenuItem {
    private String title;
    private Class<?> className;
    private String className_str;
    String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Class<?> getClassName() {
        return className;
    }

    public void setClassName(Class<?> className) {
        this.className = className;
    }

    public SlideMenuItem() {
    }

    public String getClassName_str() {
        return className_str;
    }

    public void setClassName_str(String className_str) {
        this.className_str = className_str;
    }

    public SlideMenuItem(String title, String className_str, String icon) {
        this.title = title;
        this.className_str = className_str;

        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
