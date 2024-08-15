package com.example.apicrud.mappers;

import com.example.apicrud.dtos.EntryDTO;
import com.example.apicrud.entities.Entry;
import org.springframework.stereotype.Component;

@Component
public class EntryMapper {
    public EntryDTO toEntryDTO(Entry entry) {
        return EntryDTO.builder()
                .id(entry.getId())
                .title(entry.getTitle())
                .content(entry.getContent())
                .rate(entry.getRate())
                .created(entry.getCreated())
                .author(entry.getAuthor())
                .build();
    }
}
