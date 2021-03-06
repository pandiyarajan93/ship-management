import { ShipService } from 'src/app/services/ship.service';
import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { environment } from 'src/environments/environment';
import { Ship } from '../model/ship';
import { MatSnackBarModule } from '@angular/material/snack-bar';

describe('ShipService', () => {
  let service: ShipService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, MatSnackBarModule],
      providers: [ShipService],
    });

    httpMock = TestBed.inject(HttpTestingController);
    service = TestBed.inject(ShipService);
  });

  it('should create new ship', () => {
    expect(service).toBeTruthy();
  });

  it('Should return list of ships', () => {
    const response: Ship[] = [
      {
        id: 1,
        name: 'Ship 1',
        code: 'AAAA-AAAA-A0',
        length: 0.1,
        width: 0.2,
      },
    ];

    service.getShips().subscribe((res) => {
      expect(res).toEqual(response);
    });

    const req = httpMock.expectOne(`${environment.api}/fetch`);
    expect(req.request.method).toBe('GET');
    req.flush(response);
  });

  it('should return updated ship', () => {
    const data: Ship = {
      id: 1,
      name: 'ship 1',
      code: 'AAAA-1111-A1',
      length: 12.2,
      width: 2.3,
    };

    service.updateShip(data).subscribe((res) => {
      expect(res).toEqual(data);
    });

    const req = httpMock.expectOne(`${environment.api}/update/${data.id}`);
    expect(req.request.method).toBe('PUT');
    req.flush(data);
  });
});
