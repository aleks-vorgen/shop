package com.example.demo.dao.comment;

import com.example.demo.dao.DAOAccess;
import com.example.demo.model.Comment;

import java.util.List;

public interface DAOComment extends DAOAccess {
    List<Comment> getCommentList();
    Comment getComment(int id);
    boolean insertComment(Comment comment);
    boolean updateComment(Comment comment);
    boolean deleteComment(int id);
}