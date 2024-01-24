package com.prokofeva.example.shortener.repository;

import com.prokofeva.example.shortener.doman.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
}