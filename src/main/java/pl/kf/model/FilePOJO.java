package pl.kf.model;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;

public class FilePOJO {

    private File file;
    @XmlAttribute
    private String name;

    public FilePOJO() {
    }

    public FilePOJO(File file) {
        this.file = file;
        this.name = file.getName();
    }
}
