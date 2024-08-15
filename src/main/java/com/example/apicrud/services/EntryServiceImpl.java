package com.example.apicrud.services;

import com.example.apicrud.dtos.EntryDTO;
import com.example.apicrud.entities.Entry;
import com.example.apicrud.exceptions.AppException;
import com.example.apicrud.exceptions.ErrorCode;
import com.example.apicrud.mappers.EntryMapper;
import com.example.apicrud.models.CreateEntryModel;
import com.example.apicrud.models.UpdateEntryModel;
import com.example.apicrud.repositories.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;
    private final EntryMapper entryMapper;

    @Override
    public EntryDTO createEntry(CreateEntryModel createEntryModel) {
        Entry entry = Entry.builder()
                .title(createEntryModel.getTitle())
                .content(createEntryModel.getContent())
                .rate(createEntryModel.getRate())
                .author(createEntryModel.getAuthor())
                .created(LocalDateTime.now())
                .build();
        entryRepository.save(entry);
        return entryMapper.toEntryDTO(entry);
    }

    @Override
    public EntryDTO updateEntry(Long id, UpdateEntryModel updateEntryModel) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));

        entry.setTitle(updateEntryModel.getTitle());
        entry.setContent(updateEntryModel.getContent());
        entry.setRate(updateEntryModel.getRate());
        entry.setAuthor(updateEntryModel.getAuthor());
        entryRepository.save(entry);

        return entryMapper.toEntryDTO(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        entryRepository.delete(entry);
    }

    @Override
    public EntryDTO getEntryById(Long id) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        return entryMapper.toEntryDTO(entry);
    }

    @Override
    public List<EntryDTO> getAllEntries() {
        return entryRepository.findAll()
                .stream()
                .map(entryMapper::toEntryDTO)
                .collect(Collectors.toList());
    }
}
