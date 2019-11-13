package pl.kf.scanners;
import pl.kf.model.FilePOJO;
import pl.kf.model.Folder;

import java.io.File;
import java.util.Arrays;

public class FolderScannerImpl implements FolderScanner{


    public FolderScannerImpl() {
    }

    @Override
    public Folder scan(String folderPath) {
        File root = new File(folderPath);
        Folder rootFolder = createFolder(root);
        return rootFolder;
    }

    private Folder createFolder(File folderFile) {
        File[] folderContents = folderFile.listFiles();
        Folder nextFolder = new Folder(folderFile.getName());
        Arrays.stream(folderContents).filter(File::isDirectory)
                .forEach(a -> nextFolder.getSubFolders().add(createFolder(a)));
        Arrays.stream(folderContents).filter(File::isFile)
                .forEach(a -> nextFolder.getFiles().add(new FilePOJO(a)));
        nextFolder.setNumberOfFiles(nextFolder.getFiles().size());
        return nextFolder;
    }
}
