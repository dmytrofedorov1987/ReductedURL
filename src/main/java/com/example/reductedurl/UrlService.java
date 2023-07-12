package com.example.reductedurl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UrlService {
    private final UrlRepository urlRepository;


    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Transactional
    public Long saveUrl(UrlDTO urlDTO) {
        var urlLink = urlRepository.findByUrl(urlDTO.getUrl());
        if (urlLink == null) {
            urlLink = UrlLink.of(urlDTO);
            urlRepository.save(urlLink);
        }

        return urlLink.getId();
    }

    @Transactional
    public String getFullUrl(Long id) {
        var urlOptional = urlRepository.findById(id);
        if (urlOptional.isEmpty()) {
            return null;
        }
        UrlLink urlLink = urlOptional.get();
        urlLink.setCount(urlLink.getCount() + 1);
        urlLink.setLastEnter(new Date());
        return urlLink.getUrl();
    }

    @Transactional(readOnly = true)
    public List<UrlStatDTO> getStatistics() {
        var records = urlRepository.findAll();
        var result = new ArrayList<UrlStatDTO>();

        records.forEach((a) -> result.add(a.toStatDTO()));

        return result;
    }
}
