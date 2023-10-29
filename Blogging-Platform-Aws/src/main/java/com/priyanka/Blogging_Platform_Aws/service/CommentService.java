package com.priyanka.Blogging_Platform_Aws.service;

import com.priyanka.Blogging_Platform_Aws.model.Comment;
import com.priyanka.Blogging_Platform_Aws.model.Post;
import com.priyanka.Blogging_Platform_Aws.model.User;
import com.priyanka.Blogging_Platform_Aws.repository.CommentRepo;
import com.priyanka.Blogging_Platform_Aws.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostService postService;
    @Autowired
    AuthenticationService authenticationService;

    public String addComment(String email, String token, String comment, Integer postId) {

        if(authenticationService.authenticate(email,token)) {


            Post postToBeCommented = postService.findPost(postId);

            User commenter = userRepo.findFirstByUserEmail(email);


            Comment newComment = new Comment(null,comment,LocalDateTime.now(), commenter , postToBeCommented);

            commentRepo.save(newComment);
            return commenter.getUserHandle() + " is commented on " + postToBeCommented.getPostType();
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String removeComment(String email, String tokenValue, Integer commentId) {
        if(authenticationService.authenticate(email, tokenValue)){
            Comment commentToBeDeleted = commentRepo.findById(commentId).orElseThrow();

            Post post = commentToBeDeleted.getPostOwner();
            if(authenticationService.authorizedCommentRemover(email, post, commentToBeDeleted)){
                commentRepo.delete(commentToBeDeleted);
                return "remove comment";
            }else{
                return "you are not Authorized to delete this comment";
            }
        }else{
            return "Invalid credential !!!";
        }
    }

    public List<Comment> getCommentByPostId(String email, String tokenValue, Integer postId) {
        if(authenticationService.authenticate(email, tokenValue)){
            Post myPost = postService.findPost(postId);
            List<Comment> commentsOfPost = commentRepo.findByPostOwner(myPost);
            for(Comment comment : commentsOfPost){
                comment.setPostOwner(null);
                comment.setCommenter(null);
            }
            return commentsOfPost;
        }else{
            return null;
        }
    }

    public String updateCommentByPostId(String email, String tokenValue, Integer commentId, Comment newComment) {
        if(authenticationService.authenticate(email,tokenValue)){


            User user = userRepo.findFirstByUserEmail(email);
            Comment commenter = commentRepo.findById(commentId).orElseThrow();
            if(commenter.getCommenter().equals(user)) {
                Comment comment = commentRepo.findById(commentId).orElseThrow();
                comment.setCommentBody(newComment.getCommentBody());
                comment.setCommentCreationTimeStamp(newComment.getCommentCreationTimeStamp());

                commentRepo.save(comment);
                return "comment updated";
            }else{
                return "You cannot edit comment";
            }
        }else{
            return "Invalid credential !!!";
        }
    }
}
