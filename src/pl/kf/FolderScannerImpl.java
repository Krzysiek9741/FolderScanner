package pl.kf;

import java.io.File;

public class FolderScannerImpl implements FolderScanner {


    public FolderScannerImpl() {
    }

    @Override
    public Folder scan (String folderPath){
        File root = new File(folderPath);
        Folder rootFolder = createFolder(root);
        return rootFolder;
    }

    private Folder createFolder(File folderFile){
        File[] folderContents = folderFile.listFiles();
        Folder nextFolder = new Folder(folderFile.getName());
        for (File f:folderContents) {
            if (f.isDirectory()){
                nextFolder.getSubFolders().add(createFolder(f));
            }else {
                nextFolder.getFiles().add(f);
            }
        }
        return nextFolder;
    }
}
