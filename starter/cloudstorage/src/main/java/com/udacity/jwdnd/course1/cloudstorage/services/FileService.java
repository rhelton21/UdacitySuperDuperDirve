package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {

    public List<File> allUserFiles(User user);

    public File findFile(Integer fileId);

    public int createFile(File file);

    public void deleteFile(Integer fileId);

    public boolean isfileNameAvailable(String filename);

}
