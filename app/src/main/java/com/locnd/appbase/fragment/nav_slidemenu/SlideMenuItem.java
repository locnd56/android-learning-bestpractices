package com.locnd.appbase.fragment.nav_slidemenu;

/**
 * Created by Mr.Incredible on 2/20/2016.
 */
public class SlideMenuItem {
    private String title;
    private String className;
    String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public SlideMenuItem() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public SlideMenuItem(String title, String className_str, String icon) {
        this.title = title;
        this.className = className_str;

        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
