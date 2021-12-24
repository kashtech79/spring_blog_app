package com.kash.blog.service;

import com.kash.blog.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
