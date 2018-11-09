import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bc-root',
  templateUrl: './bc-root.component.html',
  styleUrls: ['./bc-root.component.css']
})
export class BcRootComponent {
  showRootComponent : boolean = false;
  constructor() {
    if(localStorage.getItem("currentUser")){
      this.showRootComponent = true;
    }
  }


}
