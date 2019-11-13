package pl.kf.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Folder {

    @XmlAttribute
    private String name;
    @XmlElement(name="file")
    private List<FilePOJO> files = new ArrayList<>();
    @XmlElement(name="folder")
    private List<Folder> subFolders = new ArrayList<>();
    @XmlAttribute
    private Integer numberOfFiles;

    public Folder(String name) {
        this.name = name;
    }

    public Folder() {
    }

    public List<FilePOJO> getFiles() {
        return files;
    }

    public List<Folder> getSubFolders() {
        return subFolders;
    }

    public void setNumberOfFiles(Integer numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    @Override
    public String toString() {
        return name;
    }
}
