import {Component, OnInit} from '@angular/core';
import {ImageService} from "../../shared/services/image.service";
import {Constant} from "../../shared/constants/constant";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  private url = Constant.dbURL;

  showHeader: boolean = true;
  imageID: number[] = [];

  constructor(private imageService: ImageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.loadImageIDs();
  }


  loadImageIDs() {
    this.imageService.getImageId().subscribe((data) => {
      this.imageID = data;
    });
  }

  loadImage(id): string {
    return this.url + 'api/load/' + id;
  }

  deleteImage(id) {
    this.imageService.delete(id).subscribe(() => {
      }, () => {
      }, () => {
        this.loadImageIDs();
      }
    );
  }

  editImage(id) {
    this.router.navigate(['edit/', id]);
  }


}
