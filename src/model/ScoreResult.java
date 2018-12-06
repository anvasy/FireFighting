package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class ScoreResult {
    private String name;
    private LevelsEnum levelsEnum;
    private String score;
    private Date date;

    public ScoreResult() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vector returnVec() {
        Vector<String> vec = new Vector<>();
        vec.add(name);
        vec.add(levelsEnum.toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        vec.add(dateFormat.format(date.getTime()));
        vec.add(score);
        return vec;
    }

    public LevelsEnum getLevelsEnum() {
        return levelsEnum;
    }

    public void setLevelsEnum(LevelsEnum levelsEnum) {
        this.levelsEnum = levelsEnum;
    }
}
