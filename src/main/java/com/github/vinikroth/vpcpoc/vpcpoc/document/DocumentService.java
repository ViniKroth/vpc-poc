package com.github.vinikroth.vpcpoc.vpcpoc.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document create(Document document) {
        return documentRepository.save(document);
    }

    public Document findById(Long id) {
        return documentRepository.findById(id).get();
    }

    public List<Document> findAll() {
       return documentRepository.findAll();
    }

}
