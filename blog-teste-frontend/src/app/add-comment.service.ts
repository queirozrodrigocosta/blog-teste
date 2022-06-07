import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CommentPayload} from './add-comment/comment-payload';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddCommentService {

  constructor(private httpClient: HttpClient) {
  }

  addComment(commentPayload: CommentPayload){
    return this.httpClient.post('http://localhost:8080/api/comments/', commentPayload);
  }

  deleteComment(permaLink: Number){
    return this.httpClient.delete('http://localhost:8080/api/comments/' + permaLink);
  }

  filterComments(permaLink: Number): Observable<Array<CommentPayload>>{
    return this.httpClient.get<Array<CommentPayload>>("http://localhost:8080/api/comments/filter/" + permaLink);
  }

}

