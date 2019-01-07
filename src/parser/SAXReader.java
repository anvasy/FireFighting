package parser;

import model.FirehoseEnum;
import model.LevelsEnum;
import model.ScoreResult;
import model.UserSettings;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SAXReader extends DefaultHandler {
    private ArrayList<ScoreResult> scores;
    private ArrayList<FirehoseEnum> boughtHose;
    private ScoreResult scoreResult;
    private UserSettings settings;
    private ElementNames thisEl;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equals(ElementNames.user.toString())) {
            settings = new UserSettings();
            thisEl = ElementNames.user;
            return;
        }

        if (qName.equals(ElementNames.money.toString())) {
            thisEl = ElementNames.money;
            return;
        }

        if (qName.equals(ElementNames.firehose.toString())) {
            thisEl = ElementNames.firehose;
            return;
        }

        if (qName.equals(ElementNames.boughthose.toString())) {
            boughtHose = new ArrayList<>();
            thisEl = ElementNames.boughthose;
            return;
        }

        if (qName.equals(ElementNames.item.toString())) {
            thisEl = ElementNames.item;
            return;
        }

        if (qName.equals(ElementNames.scores.toString())) {
            scores = new ArrayList<>();
            thisEl = ElementNames.scores;
            return;
        }

        if (qName.equals(ElementNames.score.toString())) {
            scoreResult = new ScoreResult();
            thisEl = ElementNames.score;
            return;
        }

        if (qName.equals(ElementNames.result.toString())) {
            thisEl = ElementNames.result;
            return;
        }

        if (qName.equals(ElementNames.name.toString())) {
            thisEl = ElementNames.name;
            return;
        }

        if (qName.equals(ElementNames.level.toString())) {
            thisEl = ElementNames.level;
            return;
        }

        if (qName.equals(ElementNames.date.toString())) {
            thisEl = ElementNames.date;
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length).trim();

        if ("".equals(str))
            return;

        if (thisEl == ElementNames.name) {
            scoreResult.setName(str);
            return;
        }
        if (thisEl == ElementNames.result) {
            scoreResult.setScore(str);
            return;
        }
        if (thisEl == ElementNames.level) {
            scoreResult.setLevelsEnum(LevelsEnum.valueOf(str));
            return;
        }

        if (thisEl == ElementNames.item) {
            boughtHose.add(FirehoseEnum.valueOf(str));
            return;
        }

        if (thisEl == ElementNames.money) {
            settings.setMoney(Integer.valueOf(str));
            return;
        }

        if (thisEl == ElementNames.firehose) {
            settings.setFirehouse(FirehoseEnum.valueOf(str));
            return;
        }

        if (thisEl == ElementNames.date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            try {
                scoreResult.setDate(dateFormat.parse(str));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return;
        }

    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName)  {
        if (qName.equals(ElementNames.score.toString())) {
            scores.add(scoreResult);
            scoreResult = null;
        }

        if (qName.equals(ElementNames.boughthose.toString())) {
            settings.setBoughtHose(boughtHose);
            scoreResult = null;
        }
    }

    public ArrayList<ScoreResult> getScores() {
        ArrayList<ScoreResult> list = scores;
        scores = null;
        return list;
    }

    public UserSettings getUserSettings() {
        return settings;
    }
}
