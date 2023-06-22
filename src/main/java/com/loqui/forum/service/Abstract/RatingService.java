package com.loqui.forum.service.Abstract;

import com.loqui.forum.entity.Abstract.Rating;
import com.loqui.forum.entity.Abstract.RatingEntity;
import com.loqui.forum.entity.Enum.RatingEnum;
import com.loqui.forum.entity.Post;
import com.loqui.forum.entity.PostRating;
import com.loqui.forum.entity.User;
import com.loqui.forum.exception.EntityNotFoundException;
import com.loqui.forum.repository.Abstract.AbstractRatingRepository;

import java.util.List;
import java.util.Optional;

public abstract class RatingService<E extends Rating, E2 extends RatingEntity<E>> {
    protected final AbstractRatingRepository<E, Long> repository;

    public RatingService(AbstractRatingRepository<E, Long> repository) {
        this.repository = repository;
    }

    public abstract void changeRating(E postRating, User user, RatingEnum ratingEnum);

    public abstract void addRating(E2 post, User user, RatingEnum ratingEnum);

    public Optional<E> findById(Long id) {
        return repository.findById(id);
    };

    public void save(E rating) {
        repository.save(rating);
    }
}
