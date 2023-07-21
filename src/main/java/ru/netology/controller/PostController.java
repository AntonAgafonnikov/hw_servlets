package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
  private static final String APPLICATION_JSON = "application/json";
  private static final String ERROR = "ОШИБКА! Поста с таким id не существет!";
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var data = service.all();
    final var gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var gson = new Gson();
    final var data = service.getById(id);
    if (data == null) {
      response.getWriter().print(gson.toJson(ERROR));
    } else {
      response.getWriter().print(gson.toJson(data));
    }
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var gson = new Gson();
    final var post = gson.fromJson(body, Post.class);
    var id = post.getId();
    if(service.all().containsKey(id)) {
      service.all().put(id, post);
      response.getWriter().print(gson.toJson("Пост успешно отредактирован!"));
    } else {
      final var data = service.save(post.getContent());
      response.getWriter().print(gson.toJson(data));
    }
  }

  public void removeById(long id, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var data = service.removeById(id);
    final var gson = new Gson();
    if (data == null) {
      response.getWriter().print(gson.toJson(ERROR));
    } else {
      response.getWriter().print(gson.toJson("Пост успешно удалён!"));
    }
  }
}
