package com.kash.blog.serviceImpl;

import com.kash.blog.entity.Comment;
import com.kash.blog.entity.Post;
import com.kash.blog.exception.ResourceNotFoundException;
import com.kash.blog.payload.CommentDto;
import com.kash.blog.repository.CommentRepository;
import com.kash.blog.repository.PostRepository;
import com.kash.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository)
    {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto)
    {
        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment)
        return mapToDto(newComment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setId(commentDto.getId());
        comment.setName(commentDto.setName());
        comment.setBody(commentDto.setBody());
        return comment;
    }
}
