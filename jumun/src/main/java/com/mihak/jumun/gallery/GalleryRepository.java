package com.mihak.jumun.gallery;

import com.mihak.jumun.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Menu, Long> {
}
