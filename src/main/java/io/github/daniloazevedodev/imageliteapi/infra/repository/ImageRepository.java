package io.github.daniloazevedodev.imageliteapi.infra.repository;

import io.github.daniloazevedodev.imageliteapi.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

}
