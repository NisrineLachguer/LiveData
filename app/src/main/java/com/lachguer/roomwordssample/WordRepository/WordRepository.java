package com.lachguer.roomwordssample.WordRepository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.lachguer.roomwordssample.data.db.WordDao;
import com.lachguer.roomwordssample.data.db.WordRoomDatabase;
import com.lachguer.roomwordssample.model.Word; // Assurez-vous d'importer depuis model
import java.util.List;

public class WordRepository {
    private final WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords; // Utilisez Word du package model

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }

    public void deleteAll() {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.deleteAll();
        });
    }
}