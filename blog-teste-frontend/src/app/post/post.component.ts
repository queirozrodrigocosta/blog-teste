import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { CommentPayload } from '../add-comment/comment-payload';
import {AddPostService} from '../add-post.service';
import {PostPayload} from '../add-post/post-payload';
import {Observable} from 'rxjs';
import { AddCommentService } from '../add-comment.service';


// @ts-ignore
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  post: PostPayload;
  permaLink: Number;
  comments: Observable<Array<CommentPayload>>;


  constructor(private router: ActivatedRoute, private postService: AddPostService, private commentService: AddCommentService) {
  }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.permaLink = params['id'];
    });

    this.postService.getPost(this.permaLink).subscribe((data:PostPayload) => {
      this.post = data;

      this.comments = this.commentService.filterComments(Number(this.post.id));

    },(err: any) => {
      console.log('Failure Response');
    })
  }

  remove(id: Number) {
    this.commentService.deleteComment(id).subscribe(data => {
      this.comments = this.commentService.filterComments(Number(this.post.id));
    }, error => {
      alert(error.error.message);

    })
  }
}
