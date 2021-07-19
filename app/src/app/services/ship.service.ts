import { Ship } from './../model/ship';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class ShipService {
  api = environment.api;

  constructor(private http: HttpClient, private snackBar: MatSnackBar) {}

  public getShips(): Observable<Ship[]> {
    return this.handleApiRequest(this.http.get<Ship[]>(`${this.api}/fetch`));
  }

  public createShip(ship: Ship): Observable<Ship> {
    return this.handleApiRequest(
      this.http.post<Ship>(`${this.api}/save`, ship)
    );
  }

  public updateShip(ship: Ship): Observable<Ship> {
    return this.handleApiRequest(
      this.http.put<Ship>(`${this.api}/update/${ship.id}`, ship)
    );
  }

  public deleteShip(id: number) {
    return this.handleApiRequest(this.http.delete(`${this.api}/delete/${id}`));
  }

  public getShipById(id: number) {
    return this.handleApiRequest(this.http.get(`${this.api}/findById/${id}`));
  }

  handleApiRequest(request: any): any {
    return request.pipe(
      catchError((ex: any) => {
        this.snackBar.open('Alert', ex.error.errorMessage,{
          duration : 3000,
        });
        throw new Error(ex.error.errorMessage);
      })
    );
  }
}
