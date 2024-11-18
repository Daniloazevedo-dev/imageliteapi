package io.github.daniloazevedodev.imageliteapi.domain;

import io.github.daniloazevedodev.imageliteapi.domain.entity.Image;

import java.util.Optional;

public interface ImageService {
    Image save(Image image);
    Optional<Image> getById(String id);
}
