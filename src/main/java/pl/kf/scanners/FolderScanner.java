package pl.kf.scanners;

import pl.kf.model.Folder;

public interface FolderScanner {

    Folder scan(String filePath);
}
