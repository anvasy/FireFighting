package controller;

import model.FirehoseEnum;
import model.LevelsEnum;
import model.ScoreResult;
import model.UserSettings;
import parser.SAXReader;
import parser.WriterXML;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private UserSettings userSettings = new UserSettings();
    private ArrayList<ScoreResult> results = new ArrayList<>();
    private WriterXML writerXML;
    private SAXReader saxReader;
    private LevelsEnum levelsEnum;
    private File file;

    public Controller() {
        file = new File("scores.xml");
        writerXML = new WriterXML();
        writerXML.setFile(file);
        saxReader = new SAXReader();
        readFile();
    }

    private void writeXML() {
        try {
            writerXML.write(results, userSettings);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getMoney() {
        return userSettings.getMoney();
    }

    public void readFile() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, saxReader);
            userSettings = saxReader.getUserSettings();
            results = saxReader.getScores();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<ScoreResult> getScores() {
        readFile();
        return results;
    }

    public void updateMoney() {
        userSettings.addMoney(levelsEnum.getSalary());
        writeXML();
    }

    public void updateScores(ScoreResult result) {
        results.add(result);
    }

    public void clearScores() {
        results.clear();
        writeXML();
    }

    public void clearAll() {
        results.clear();
        userSettings.setFirehouse(FirehoseEnum.STANDART);
        userSettings.setMoney(0);
        userSettings.getBoughtHose().clear();
        writeXML();
    }

    public void setFirehose(FirehoseEnum firehose) {
        userSettings.setFirehouse(firehose);
        writeXML();
    }

    public LevelsEnum getLevelsEnum() {
        return levelsEnum;
    }

    public void setLevelsEnum(LevelsEnum levelsEnum) {
        this.levelsEnum = levelsEnum;
    }

    public boolean hasFirehose(FirehoseEnum firehoseEnum) {
        return userSettings.getBoughtHose().contains(firehoseEnum);
    }

    public boolean buyHose(FirehoseEnum firehoseEnum) {
        if(userSettings.getMoney() < firehoseEnum.getCost())
            return false;

        userSettings.withdrawMoney(firehoseEnum.getCost());
        List<FirehoseEnum> firehose = userSettings.getBoughtHose();
        firehose.add(firehoseEnum);
        userSettings.setBoughtHose(firehose);
        userSettings.setFirehouse(firehoseEnum);
        writeXML();

        return true;
    }

    public FirehoseEnum getHose() {
        return userSettings.getFirehouse();
    }
}
