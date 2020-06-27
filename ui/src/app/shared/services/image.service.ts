import {Injectable} from '@angular/core';
import {Constant} from "../constants/constant";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Image} from "../models/image.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private url = Constant.dbURL;

  constructor(private http: HttpClient) {
  }

  getImageId(): Observable<number[]> {
    const url = this.url + 'api/load/';
    return this.http.get<number[]>(url);
  }

  getById(id): Observable<Image> {
    const url = this.url + 'api/image/' + id;
    return this.http.get<Image>(url);
  }

  get(): Observable<Image[]> {
    const url = this.url + 'api/image/';
    return this.http.get<Image[]>(url);
  }

  save(image: Image): Observable<Image> {
    const url = this.url + 'api/image/';
    const data = new FormData();
    data.append("image", image.image);
    data.append("id", String(image.id));
    return this.http.post<Image>(url, data);
  }

  update(image: Image): Observable<Image> {
    const url = this.url + 'api/image/';
    const data = new FormData();
    data.append("image", image.image);
    data.append("id", String(image.id));
    return this.http.put<Image>(url, data);
  }

  delete(id): Observable<void> {
    const url = this.url + 'api/image/' + id;
    return this.http.delete<void>(url);
  }
}
