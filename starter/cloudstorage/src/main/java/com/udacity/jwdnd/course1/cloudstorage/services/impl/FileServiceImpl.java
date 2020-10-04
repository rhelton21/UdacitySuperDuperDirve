package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private FileMapper fileMapper;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public List<File> allUserFiles(User user) {
        return this.fileMapper.findfiles(user.getUserid());
    }

    @Override
    public File findFile(Integer fileId) {
        return this.fileMapper.findfile(fileId);
    }

    @Override
    public int createFile(File file) {
        return this.fileMapper.insert(file);
    }

    @Override
    public void deleteFile(Integer fileId) {
        this.fileMapper.deletefile(fileId);
    }

    @Override
    public boolean isfileNameAvailable(String filename) {
        return this.fileMapper.findFileByName(filename) == null;
    }

}
