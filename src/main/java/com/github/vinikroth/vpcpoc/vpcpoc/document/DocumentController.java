package com.github.vinikroth.vpcpoc.vpcpoc.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController("/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<DocumentResponseDto> create(@RequestBody DocumentRequestDto body) {
        return Optional.of(body)
                .map(request -> objectMapper.convertValue(request, Document.class))
                .map(documentService::create)
                .map(document -> objectMapper.convertValue(document, DocumentResponseDto.class))
                .map(response -> new ResponseEntity<>(response, HttpStatus.CREATED))
                .get();
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponseDto>> findAll() {
        return new ResponseEntity<>(documentService.findAll()
                .stream()
                .map(entity -> objectMapper.convertValue(entity, DocumentResponseDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                objectMapper.convertValue(documentService.findById(id), DocumentResponseDto.class), HttpStatus.OK);
    }
}
