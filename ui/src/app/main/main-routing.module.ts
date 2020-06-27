import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {MainPageComponent} from "./main-page/main-page.component";
import {EditImageComponent} from "./edit-image/edit-image.component";
import {MainlayoutComponent} from "./mainlayout/mainlayout.component";


const routes: Routes = [
  {
    path: '', component: MainlayoutComponent,
    children: [
      {path: '', component: MainPageComponent},
      {path: 'main', component: MainPageComponent},
      {path: 'edit/:action', component: EditImageComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule {
}
