package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteMapper noteMapper) {
        logger.debug("NoteServiceImpls");
        this.noteMapper = noteMapper;
    }

    @Override
    public List<Note> allUserNotes(User user) {
        return this.noteMapper.findNotes(user.getUserid());
    }

    @Override
    public Note findNote(Integer noteId) {
        return this.noteMapper.findNote(noteId);
    }

    @Override
    public int createNote(Note note) {
        return this.noteMapper.insert(note);
    }

    @Override
    public int update(Note note) {
        return this.noteMapper.update(note);
    }

    @Override
    public void deleteNote(Integer noteId) {
        this.noteMapper.deleteNote(noteId);
    }

}
