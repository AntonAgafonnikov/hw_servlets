package ru.netology.repository;

import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// Stub
public class PostRepository {
  private final ConcurrentMap<Long, Post> posts = new ConcurrentHashMap<Long, Post>();
  public ConcurrentMap<Long, Post> all() {
    return posts;
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Long id, Post post) {
    posts.put(id, post);
    return post;
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}
