import { Component, OnInit } from '@angular/core';
import {AddPostService} from '../add-post.service';
import {Observable} from 'rxjs';
import {PostPayload} from '../add-post/post-payload';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Observable<Array<PostPayload>>;
  postPayload: PostPayload;

  constructor(private addpostService: AddPostService, private postService: AddPostService) { }

  ngOnInit() {
    this.posts = this.postService.getAllPosts();
  }

  remove(model: any) {
    this.postPayload = model;
    this.addpostService.deletePost(model.id).subscribe(data => {
      this.posts = this.postService.getAllPosts();
    }, error => {
      alert(error.error.message);

    })
  }

}
