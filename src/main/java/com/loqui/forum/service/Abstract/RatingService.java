package com.loqui.forum.service.Abstract;

import com.loqui.forum.entity.Abstract.RatingEntity;
import com.loqui.forum.entity.User;
import com.loqui.forum.exception.EntityNotFoundException;
import com.loqui.forum.repository.Abstract.AbstractRatingRepository;

import java.util.Optional;

public abstract class RatingService<E extends RatingEntity> {
    protected final AbstractRatingRepository<E, Long> repository;

    public RatingService(AbstractRatingRepository<E, Long> repository) {
        this.repository = repository;
    }

    public void addPositiveRating(E ratingEntity) {
        ratingEntity.setPositiveRating(ratingEntity.getPositiveRating() + 1);
        repository.save(ratingEntity);
    }

    public void addNegativeRating(E ratingEntity) {
        ratingEntity.setNegativeRating(ratingEntity.getNegativeRating() + 1);
        repository.save(ratingEntity);
    }

    public abstract void addPositiveRating(E ratingEntity, User user);
    public abstract void addNegativeRating(E ratingEntity, User user);

    public Optional<E> findById(Long id) {
        return repository.findById(id);
    };

    public void save(E rating) {
        repository.save(rating);
    }
}
