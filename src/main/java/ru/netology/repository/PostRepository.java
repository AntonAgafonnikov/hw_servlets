package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  private final ConcurrentMap<Long, Post> posts = new ConcurrentHashMap<>();
  private static final AtomicLong countPostID = new AtomicLong(0);
  public List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {
    if(posts.containsKey(id)) {
      return Optional.ofNullable(posts.get(id));
    } else {
      return Optional.empty();
    }
  }

  public Post save(Post post) {
    if(posts.containsKey(post.getId())) {
      posts.put(post.getId(), post);
    } else {
      post.setId(countPostID.incrementAndGet());
      posts.put(countPostID.get(), post);
    }
    return post;
  }

  public boolean removeById(long id) {
    if(posts.containsKey(id)) {
      posts.remove(id);
      return true;
    } else {
      return false;
    }
  }
}
