package com.example.repository;

import com.example.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByLink(String link);
    Optional<Link> findByShortLink(String shortLink);
}
