import { Component, Input, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';


@Component({
  selector: 'app-new-ship',
  templateUrl: './new-ship.component.html',
  styleUrls: ['./new-ship.component.css']
})
export class NewShipComponent implements OnInit {

  name: string ="";
  code: string ="";
  length: number =0;
  width: number = 0;

  constructor(public dialog: MatDialog) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(NewShipComponent, {
      width: '250px',
      data: {name: this.name, animal: this.code, length: this.length, width: this.width}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }

  ngOnInit(): void {
  }

  
}

export interface PopUpData {
  name: string;
  code: string;
  length: number;
  width : number;
}