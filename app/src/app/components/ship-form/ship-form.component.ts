import { Ship } from './../../model/ship';
import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { customValidator } from './customValidator';
import { ShipService } from 'src/app/services/ship.service';

@Component({
  selector: 'ship-form',
  templateUrl: './ship-form.component.html',
  styleUrls: ['./ship-form.component.css'],
})
export class ShipFormComponent {
  shipForm: FormGroup;

  isUpdate = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) data: Ship,
    public dialog: MatDialogRef<ShipFormComponent>
  ) {
    this.shipForm = new FormGroup({
      id: new FormControl(),
      code: new FormControl('', [Validators.required, customValidator]),
      name: new FormControl('', [Validators.required]),
      length: new FormControl(null, [
        Validators.required,
        Validators.max(1000),
        Validators.pattern(/^\d/),
      ]),
      width: new FormControl(null, [
        Validators.required,
        Validators.max(1000),
        Validators.pattern(/^\d/),
      ]),
    });

    if (data && Object.keys(data).length) {
      this.isUpdate = true;
      this.setData(data);
    }
  }

  setData(data: Ship): void {
    const { id, code, name, length, width } = data;
    this.shipForm.setValue({
      id,
      code,
      name,
      length,
      width,
    });
  }

  save() {
    this.dialog.close(this.shipForm.value);
  }

  close() {
    this.dialog.close();
  }
}
