package com.loqui.forum.repository.Abstract;


import com.loqui.forum.entity.PostRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AbstractRatingRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}