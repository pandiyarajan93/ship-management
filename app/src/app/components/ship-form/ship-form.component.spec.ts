import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReactiveFormsModule } from '@angular/forms';
import {
  MatDialogModule,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ShipFormComponent } from './ship-form.component';

describe('ShipFormComponent', () => {
  let component: ShipFormComponent;
  let fixture: ComponentFixture<ShipFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShipFormComponent],
      imports: [
        MatFormFieldModule,
        ReactiveFormsModule,
        MatInputModule,
        BrowserAnimationsModule,
        MatDialogModule,
      ],
      providers: [
        {
          provide: MatDialogRef,
          useValue: {
            id: 1,
          },
        },
        {
          provide: MAT_DIALOG_DATA,
          useValue: {},
        },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should not disable submit button when form is valid', async(() => {
    component.shipForm.controls['name'].setValue('test');
    component.shipForm.controls['code'].setValue('xxxx-0000-x0');
    component.shipForm.controls['length'].setValue(0.1);
    component.shipForm.controls['width'].setValue(0.2);
    expect(component.shipForm.valid).toBeTrue();

    fixture.detectChanges();
    let btn = fixture.debugElement.nativeElement.querySelector('#submit');
    expect(btn.disabled).toBeFalsy();
  }));
});
