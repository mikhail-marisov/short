package com.example.repository;

import com.example.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByLink(String link);
    Optional<Link> findByShortLink(String shortLink);

}
