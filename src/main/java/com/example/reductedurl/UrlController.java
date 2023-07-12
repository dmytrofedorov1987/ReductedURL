package com.example.reductedurl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public UrllinkResultDTO shorten(@RequestBody UrlDTO urlDTO) {
        Long id = urlService.saveUrl(urlDTO);
        var result = new UrllinkResultDTO();
        result.setUrl(urlDTO.getUrl());
        result.setChortUrl(Long.toString(id));
        return result;
    }

    @GetMapping("my/{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") Long id) {

        var url = urlService.getFullUrl(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));
        headers.setCacheControl("no - cache, no - store, must - revalidate");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/stat")
    public List<UrlStatDTO> stats() {
        return urlService.getStatistics();
    }


}
