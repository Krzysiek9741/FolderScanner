package pl.kf;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class MyXMLWriter {

    private FolderScanner folderScanner;

    public MyXMLWriter(FolderScanner folderScanner) {
        this.folderScanner = folderScanner;
    }

        public void writeFolderToXml(String xmlFilePath, String folderPath) {

            try {
                Folder rootFolder = folderScanner.scan(folderPath);

                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

                Document document = documentBuilder.newDocument();

                Element root = document.createElement("folder");
                Attr rootAttr = document.createAttribute("name");
                rootAttr.setValue(rootFolder.getName());
                root.setAttributeNode(rootAttr);
                document.appendChild(root);

                appendFiles(rootFolder, root, document);
                appendFolders(rootFolder, root, document);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));

                transformer.transform(domSource, streamResult);

                System.out.println("Done creating XML File");

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }

        private void appendFiles(Folder folder, Element element, Document document){
            for (File f: folder.getFiles()) {
                Element file = document.createElement("file");
                Attr attr = document.createAttribute("name");
                attr.setValue(f.getName());
                file.setAttributeNode(attr);

                element.appendChild(file);
            }
        }

    private void appendFolders(Folder folder, Element element, Document document){
        for (Folder f: folder.getSubFolders()) {
            Element folderElement = createElement(document, f);
            element.appendChild(folderElement);

            appendFiles(f, folderElement, document);
            appendFolders(f, folderElement, document);
        }
    }

    private Element createElement(Document document, Folder folder){
        Element folderElement = document.createElement("folder");
        Attr attr = document.createAttribute("name");
        attr.setValue(folder.getName());
        folderElement.setAttributeNode(attr);
        return folderElement;
    }
}
