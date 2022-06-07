package com.teste.blog.security;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teste.blog.dto.CommentDto;
import com.teste.blog.model.Comments;
import com.teste.blog.repository.CommentRepository;
import com.teste.blog.service.AuthService;

@Service
public class CommentService {

    @Autowired
    private AuthService authService;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public List<CommentDto> getComments(Long id) {
        List<Comments> comments = commentRepository.findAllByIdPost(id);
        return comments.stream().map(this::mapFromCommentsToDto).collect(toList());
    }

    @Transactional
    public void createComments(CommentDto commentDto) {
        Comments comment = mapFromDtoToComments(commentDto);
        commentRepository.save(comment);
    }

    @Transactional
	public void deleteComments(Long id) {
        Comments comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comentário não encontrato"));
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("Usuário não encontrato"));
        if(comment.getUsername().equals(loggedInUser.getUsername()))
        	commentRepository.delete(comment);	
        else
        	throw new IllegalArgumentException("Apenas o criador do commentário poderá ter permissão para excluí-lo!");
        	
	}


    private CommentDto mapFromCommentsToDto(Comments comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setComment(comment.getComment());
        commentDto.setUsername(comment.getUsername());
        commentDto.setIdPost(comment.getIdPost());
        return commentDto;
    }

    private Comments mapFromDtoToComments(CommentDto commentDto) {
        Comments comment = new Comments();
        comment.setComment(commentDto.getComment());
        comment.setIdPost(commentDto.getIdPost());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("Usuário não encontrato"));
        comment.setUsername(loggedInUser.getUsername());
        return comment;
    }


}
