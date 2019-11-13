package pl.kf;

import pl.kf.scanners.FolderScannerImpl;
import pl.kf.writer.MyXMLWriter;

public class Main {

    public static void main(String[] args) {

        String xmlPath = "abc.xml";
        String folderPath = "C:\\Root";

        MyXMLWriter myXMLWriter = new MyXMLWriter(new FolderScannerImpl());

        myXMLWriter.writeFolderToXml(xmlPath, folderPath);
    }
}
