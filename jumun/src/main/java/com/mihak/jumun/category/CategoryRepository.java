package com.mihak.jumun.category;

import com.mihak.jumun.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/*TypeMismatchException 에러 해결*/
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);

}
