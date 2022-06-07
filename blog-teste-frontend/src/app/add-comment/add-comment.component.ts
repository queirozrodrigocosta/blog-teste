import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {CommentPayload} from './comment-payload';
import {AddCommentService} from '../add-comment.service';
import {Router} from '@angular/router';
import {ActivatedRoute} from '@angular/router';



@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  addCommentForm: FormGroup;
  commentPayload: CommentPayload;
  comment = new FormControl('');
  body = new FormControl('');
  permaLink: Number;


  constructor(private addcommentService: AddCommentService, private router: Router, private routers: ActivatedRoute, ) {
    this.addCommentForm = new FormGroup({
      comment: this.comment,
      body: this.body
    });
    this.commentPayload = {
      id: '',
      comment: '',
      username: '',
      idPost: ''
    }
  }

  ngOnInit() {
    this.routers.params.subscribe(params => {
      this.permaLink = params['id'];
    });
  }

  addComment() {
    this.commentPayload.idPost = this.permaLink.toString();
    this.commentPayload.comment = this.addCommentForm.get('body').value;
    this.addcommentService.addComment(this.commentPayload).subscribe(data => {
      this.router.navigateByUrl('/post/' + this.permaLink);
    }, error => {
      alert(error.error.message);
    })
  }
}
