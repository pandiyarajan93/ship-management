import { Ship } from './../../model/ship';
import { ShipService } from 'src/app/services/ship.service';
import { MatTableModule } from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { RouterTestingModule } from '@angular/router/testing';

import { ShipDetailsComponent } from './ship-details.component';
import { of } from 'rxjs';

describe('ShipDetailsComponent', () => {
  let component: ShipDetailsComponent;
  let fixture: ComponentFixture<ShipDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShipDetailsComponent],
      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatDialogModule,
        MatIconModule,
        MatTableModule
      ],
      providers: [ShipService]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    const ship: Ship []= [{ name: 'test', code: 'xxx-xxx-x0', length: 0.1, width: 0.2 }];

    spyOn(component['shipService'], 'getShips')
      .and.callFake(() => of(ship));
  });

  it('should open ship form dialog', () => {
    spyOn(<any>component['dialog'], 'open').and.returnValue({
      afterClosed() {
        return of(true);
      },
    });

    component.createOrUpdate();

    expect(component['dialog'].open).toHaveBeenCalled();
  });



  it('should create', () => {
    const ship: Ship = { name: 'test', code: 'xxx-xxx-x0', length: 0.1, width: 0.2 };
    spyOn(<any>component['dialog'], 'open').and.returnValue({
      afterClosed() {
        return of(ship);
      },
    });

    spyOn(<any>component['shipService'], 'createShip')
      .and.callFake(() => of(ship));

    const icon = fixture.debugElement.nativeElement.querySelector('#create-ship-icon');
    icon.click();

    expect(<any>component['dialog'].open).toHaveBeenCalled();
    expect(<any>component['shipService'].createShips).toHaveBeenCalled();
    expect(component).toBeTruthy();
  });

});
