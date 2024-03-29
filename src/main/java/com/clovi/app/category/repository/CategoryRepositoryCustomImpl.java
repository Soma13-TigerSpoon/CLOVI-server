package com.clovi.app.category.repository;

import com.clovi.app.category.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.clovi.app.category.QCategory.category;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  @Override
  public List<Category> getAllCategory() {
    List<Category> queryResults = queryFactory
            .selectFrom(category)
            .where(category.ParentCategory.isNull())
            .orderBy(category.orders.asc())
            .fetch();

    return queryResults;
  }

  @Override
  public List<Category> getChildCategoriesByParentId(Long id) {
    List<Category> queryResults = queryFactory
            .selectFrom(category)
            .where(category.ParentCategory.id.eq(id))
            .fetch();

    return queryResults;
  }
}
