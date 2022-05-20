package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.domain.Comment;
import com.nhnacademy.jdbc.board.compre.dto.CommentDTO;
import com.nhnacademy.jdbc.board.compre.mapper.CommentMapper;
import com.nhnacademy.jdbc.board.compre.service.CommentService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DefaultCommentService implements CommentService {

    private final CommentMapper commentMapper;
    private final UserService userService;

    public DefaultCommentService(CommentMapper commentMapper, DefaultUserService userService) {
        this.commentMapper = commentMapper;
        this.userService = userService;
    }

    @Override
    public Optional<Comment> getComment(int id) {
        if (Objects.isNull(commentMapper.selectComment(id))) {
            return Optional.empty();
        }
        return commentMapper.selectComment(id);
    }

    @Override
    public List<CommentDTO> getComments(int id) {
        List<Comment> com = commentMapper.selectComments(id);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : com) {
            commentDTOS.add(new CommentDTO(comment.getCommentNo(),(userService.getUserId(comment.getUserNo())),
                comment.getCommentContent()));
        }
        return commentDTOS;
    }

    @Override
    public void register(int postNo, int userNo, String comment) {
        commentMapper.commentRegister(postNo, userNo, comment);
    }

    @Override
    public void update(int id, String content) {
        commentMapper.commentUpdate(id, content);
    }

    @Override
    public void delete(int id) {
        commentMapper.commentDelete(id);
    }
}
