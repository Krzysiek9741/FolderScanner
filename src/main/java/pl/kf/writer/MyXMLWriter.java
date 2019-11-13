package pl.kf.writer;
import pl.kf.model.Folder;
import pl.kf.scanners.FolderScanner;

import javax.xml.bind.*;
import java.io.File;

public class MyXMLWriter {

    private FolderScanner folderScanner;

    public MyXMLWriter(FolderScanner folderScanner) {
        this.folderScanner = folderScanner;
    }

    public void writeFolderToXml(String xmlFilePath, String folderPath){
        try {
            JAXBContext ctx = JAXBContext.newInstance(Folder.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Folder rootFolder = folderScanner.scan(folderPath);
            marshaller.marshal(rootFolder, new File(xmlFilePath));
        } catch (JAXBException e){
            e.printStackTrace();
        }
    }

    public Folder readXml(String xmlFilePath){
        Folder folder = null;
        try {
            JAXBContext ctx = JAXBContext.newInstance(Folder.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            folder = (Folder) unmarshaller.unmarshal(new File(xmlFilePath));
        }catch (JAXBException e){
            e.printStackTrace();
        }
        return folder;
    }
}
