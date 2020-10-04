package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {

    public List<Note> allUserNotes(User user);

    public Note findNote(Integer noteId);

    public int createNote(Note note);

    public int update(Note note);

    public void deleteNote(Integer noteId);

}
