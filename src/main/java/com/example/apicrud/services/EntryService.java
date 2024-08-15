package com.example.apicrud.services;

import com.example.apicrud.dtos.EntryDTO;
import com.example.apicrud.models.CreateEntryModel;
import com.example.apicrud.models.UpdateEntryModel;

import java.util.List;

public interface EntryService {
    EntryDTO createEntry(CreateEntryModel createEntryModel);
    EntryDTO updateEntry(Long id, UpdateEntryModel updateEntryModel);
    void deleteEntry(Long id);
    EntryDTO getEntryById(Long id);
    List<EntryDTO> getAllEntries();
}
