//package com.dsmpear.main.user_backend_v2.controller;
//
//import com.dsmpear.main.payload.request.CommentRequest;
//import com.dsmpear.main.service.comment.CommentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RequiredArgsConstructor
//@RequestMapping("/comment")
//@RestController
//public class CommentController {
//
//    private final CommentService commentService;
//
//    @PostMapping("/{reportId}")
//    public void writeComment(@RequestBody CommentRequest commentRequest, @PathVariable Integer reportId) {
//        commentService.createComment(commentRequest, reportId);
//    }
//
//    @DeleteMapping("/{commentId}")
//    public void deleteComment(@PathVariable Integer commentId) {
//        commentService.deleteComment(commentId);
//    }
//
//    @PatchMapping("/{commentId}")
//    public void updateComment(@PathVariable Integer commentId, @RequestParam String content) {
//        commentService.updateComment(commentId, content);
//    }
//
//}
