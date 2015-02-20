package com.games.oleg.snake.back;

import android.content.res.XmlResourceParser;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg.shlemin on 19.02.2015.
 */
public class XmlParser {

    private static final String ns = null;

    public void parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            readFeed(parser);
        } finally {
            in.close();
        }
    }

    public void parse(XmlResourceParser parser) throws XmlPullParserException, IOException {
        try {
            readLevel(parser);
        } finally {
            parser.close();
        }
    }


    private void readLevel(XmlPullParser parser) throws XmlPullParserException, IOException {
        //parser.require(XmlPullParser.START_TAG, "ns", "level");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry level
            if (name.equals("level")) {
                readFeed(parser);
            }
        }
    }


    private void readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {

        String sizeX;
        String sizeY;
        String startX;
        String startY;
        String obstaclesStr;
        ArrayList<Position> obstacles = new ArrayList<Position>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("size_x")) {
                sizeX = readSizeX(parser);
            } else if (name.equals("size_y")) {
                sizeY = readSizeY(parser);
            } else if (name.equals("start_x")) {
                startX = readIntParameter(parser);
            } else if (name.equals("start_y")) {
                startY = readIntParameter(parser);
            } else if (name.equals("obstacles")) {
                obstaclesStr = readObstacles(parser);
                obstacles = CoordinatesStringToPositions(obstaclesStr);
            } else {
                skip(parser);
            }
        }

        String boo="";
    }


    private ArrayList<Position> CoordinatesStringToPositions(String coordinatesString) {
        ArrayList<Position> obstacles = new ArrayList<Position>();
        String[] coordinates = coordinatesString.split("/n");
        for (String coordinatesPairStr: coordinates) {
            String[] coordinatesPair = coordinatesPairStr.split(",");
            String xStr =  coordinatesPair[0].replaceAll("\\s+", "");
            String yStr = coordinatesPair[1].replaceAll("\\s+", "");
            int x = Integer.parseInt("xStr");
            int y = Integer.parseInt("yStr");
            obstacles.add(new Position(x, y));
        }

        return obstacles;
    }


    private String readIntParameter(XmlPullParser parser) throws IOException, XmlPullParserException {
        String parameter = readText(parser);
        return parameter;
    }


    private String readSizeX(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "size_x");
        String size_x = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "size_x");
        return size_x;
    }

    private String readSizeY(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "size_y");
        String size_y = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "size_y");
        return size_y;
    }
    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private String readObstacles(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "obstacles");
        String obstacles = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "obstacles");
        return obstacles;


    }


/*
    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
    private String readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "entry");
        List entries = new ArrayList();
        String title = null;
        String summary = null;
        String link = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                title = readTitle(parser);
            } else if (name.equals("summary")) {
                summary = readSummary(parser);
            } else if (name.equals("link")) {
                link = readLink(parser);
            } else {
                skip(parser);
            }
        }
        return "";
        //return new Map.Entry(title, summary, link);
    }*/

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}