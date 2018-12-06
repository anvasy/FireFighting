package parser;

import model.FirehoseEnum;
import model.ScoreResult;
import model.UserSettings;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

public class WriterXML {
    private File file;
    private Document doc;
    private List<ScoreResult> scores;
    private UserSettings settings;

    public WriterXML(File file, List<ScoreResult> scores, UserSettings settings) {
        this.file = file;
        this.scores = scores;
        this.settings = settings;
    }

    public WriterXML(List<ScoreResult> scores, UserSettings settings) {
        this.scores = scores;
        this.settings = settings;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void write() throws TransformerException, ParserConfigurationException {
        if (file != null && scores != null && settings != null) {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element elementUser = doc.createElement(ElementNames.user.toString());
                Element elementMoney =  doc.createElement(ElementNames.money.toString());
                elementMoney.setTextContent(String.valueOf(settings.getMoney()));
                elementUser.appendChild(elementMoney);

                Element elementFirehose = doc.createElement(ElementNames.firehose.toString());
                elementFirehose.setTextContent(String.valueOf(settings.getFirehouse()));
                elementUser.appendChild(elementFirehose);

                Element elementBought = doc.createElement(ElementNames.boughthose.toString());
                for(FirehoseEnum firehoseEnum : settings.getBoughtHose()) {
                    Element elementItem = doc.createElement(ElementNames.item.toString());
                    elementItem.setTextContent(String.valueOf(firehoseEnum));
                    elementBought.appendChild(elementItem);
                }
                elementUser.appendChild(elementBought);

            Element elementScores = doc.createElement(ElementNames.scores.toString());
                for(ScoreResult result : scores) {
                    Element elementScore = doc.createElement(ElementNames.score.toString());

                        Element elementName = doc.createElement(ElementNames.name.toString());
                        elementName.setTextContent(result.getName());
                        elementScore.appendChild(elementName);

                        Element elementLevel = doc.createElement(ElementNames.level.toString());
                        elementLevel.setTextContent(String.valueOf(result.getLevelsEnum()));
                        elementScore.appendChild(elementLevel);

                        Element elementResult = doc.createElement(ElementNames.result.toString());
                        elementResult.setTextContent(result.getScore());
                        elementScore.appendChild(elementResult);

                        Element elementDate = doc.createElement(ElementNames.date.toString());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
                        elementDate.setTextContent(dateFormat.format(result.getDate()));
                        elementScore.appendChild(elementDate);

                    elementScores.appendChild(elementScore);
                }
            elementUser.appendChild(elementScores);

            doc.appendChild(elementUser);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        }
    }
}