package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class PostService {
  private final PostRepository repository;
  private static long countPostID = 1;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public ConcurrentMap<Long, Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    Post post = null;
    if (all().containsKey(id)) {
      post = all().get(id);
      repository.getById(id);
    }
    return post;
  }

  public synchronized Post save(String content) {
    Post post = new Post(countPostID, content);
    return repository.save(countPostID++, post);
  }

  public Post removeById(long id) {
    Post post = null;
    if (all().containsKey(id)) {
      post = all().get(id);
      repository.removeById(id);
    }
    return post;
  }
}

