import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/23 15:01
 * Modified:
 * Description:
 */
public class XmlParseUtil {

    public static void parseXml(String xmlPath) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(xmlPath);
            // 获取根节点
            Element programSeries = document.getRootElement();
            for (Iterator programSerialIterator = programSeries.elementIterator(); programSerialIterator.hasNext(); ) {
                Element programSerial = (Element) programSerialIterator.next();
                // 获取节点信息
                System.out.println(programSerial.elementText("id"));
                System.out.println(programSerial.elementText("name"));
                Element programsElement = programSerial.element("programs");
                for (Iterator programsIterator = programsElement.elementIterator("program"); programsIterator.hasNext(); ) {
                    Element program = (Element) programsIterator.next();
                    System.out.println(program.elementText("programSeriesId"));
                    System.out.println(program.elementText("partNum"));
                    Element mediasElement = program.element("medias");
                    for (Iterator mediasIterator = mediasElement.elementIterator("media"); mediasIterator.hasNext(); ) {
                        Element medias = (Element) mediasIterator.next();
                        System.out.println(medias.elementText("cpCode"));
                        System.out.println(medias.elementText("mid"));
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
