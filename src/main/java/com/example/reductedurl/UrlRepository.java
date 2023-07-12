package com.example.reductedurl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlLink, Long> {
    UrlLink findByUrl(String url);

    Optional<UrlLink> findById(Long id);
}
