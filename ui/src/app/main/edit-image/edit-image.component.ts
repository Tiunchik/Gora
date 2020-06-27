import {Component, OnInit} from '@angular/core';
import {Image} from "../../shared/models/image.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ImageService} from "../../shared/services/image.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Constant} from "../../shared/constants/constant";

@Component({
  selector: 'app-edit-image',
  templateUrl: './edit-image.component.html',
  styleUrls: ['./edit-image.component.css']
})
export class EditImageComponent implements OnInit {

  private url = Constant.dbURL;
  showPreview: boolean = false;
  previewImage: string;
  form: FormGroup;
  file: File;
  image: Image = {};
  id: string;
  public valid: boolean = true;

  constructor(private fb: FormBuilder,
              private imageService: ImageService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.initForm();
    this.checkEdit();
  }

  checkEdit() {
    this.id = this.route.snapshot.params.action;
    if (this.id && this.id != 'new') {
      this.previewImage = this.url + 'api/load/' + this.id;
      this.showPreview = true;
    }
  }

  initForm() {
    this.form = this.fb.group({
      bytes: [null]
    })
  }

  loadBytes(event) {
    this.file = event.target.files[0];
    if (event.target.files.length > 0) {
      this.form.patchValue({
        bytes: this.file
      })
    }
    this.form.get('bytes').updateValueAndValidity();
    this.showImage();
    this.valid = false;
  }

  showImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.previewImage = reader.result as string;
    };
    reader.readAsDataURL(this.file);
    this.showPreview = true;
  }

  submitImage() {
    if (this.id == 'new') {
      console.log('step 2');
      this.image.image = this.file;
      this.image.id = 0;
      this.imageService.save(this.image).subscribe(() => {
      }, () => {
      }, () => {
        this.router.navigate(['']);
      });
    } else {
      this.image.image = this.file;
      this.image.id = Number(this.id);
      this.imageService.update(this.image).subscribe(() => {
      }, () => {
      }, () => {
        this.router.navigate(['']).then(() => {
          window.location.reload();});
      });
    }
  }

}
