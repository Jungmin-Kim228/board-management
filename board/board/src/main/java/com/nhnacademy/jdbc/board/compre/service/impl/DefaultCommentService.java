package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.dao.CommentDAO;
import com.nhnacademy.jdbc.board.compre.domain.Comment;
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
    public Optional<CommentDAO> getComment(int id) {
        if (Objects.isNull(commentMapper.selectComment(id))) {
            return Optional.empty();
        }
        return commentMapper.selectComment(id);
    }

    @Override
    public List<Comment> getComments(int id) {
        List<CommentDAO> com = commentMapper.selectComments(id);
        List<Comment> comments = new ArrayList<>();
        for (CommentDAO commentDAO : com) {
            comments.add(new Comment(commentDAO.getCommentNo(),(userService.getUserId(commentDAO.getUserNo())),
                commentDAO.getCommentContent()));
        }
        return comments;
    }

    @Override
    public void register(Comment comment) {
        commentMapper.commentRegister(comment);
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
