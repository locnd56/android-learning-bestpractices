package com.example.exampleanalytics.parser;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.example.exampleanalytics.fragment.nav_slidemenu.SlideMenuItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Incredible on 2/23/2016.
 */
public class XMLParser {
    private Context context;
    public XMLParser(Context context) {
        this.context = context;
    }

    public static List<SlideMenuItem> parseXML(XmlResourceParser xml) {
        SlideMenuItem menuItem = null;
        List<SlideMenuItem> menuItemList = new ArrayList<>();
        try {
            while (xml.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xml.getEventType();
                String tagname = xml.getName();
                if (eventType == XmlPullParser.START_TAG) {
                    if (tagname.equals("menu")) {
                        String name = xml.getAttributeValue(null, "name");
                        String className = xml.getAttributeValue(null, "class");
                        String icon = xml.getAttributeValue(null, "icon");
                        menuItem = new SlideMenuItem(name, className, icon);
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (tagname.equals("menu")) {
                        menuItemList.add(menuItem);
                    }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuItemList;
    }
}
