package pl.kf;

public class Main {

    public static void main(String[] args) {

        String xmlPath = "C:\\XML File\\ABC.xml";
        String folderPath = "C:\\Root";

        MyXMLWriter myXMLWriter = new MyXMLWriter(new FolderScannerImpl());

        myXMLWriter.writeFolderToXml(xmlPath, folderPath);
    }
}
