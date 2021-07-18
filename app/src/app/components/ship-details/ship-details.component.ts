import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Ship } from 'src/app/model/ship';
import { ShipService } from 'src/app/services/ship.service';
import { ShipFormComponent } from '../ship-form/ship-form.component';

@Component({
  selector: 'ship-details',
  templateUrl: './ship-details.component.html',
  styleUrls: ['./ship-details.component.css'],
})
export class ShipDetailsComponent implements OnInit {
  displayedColumns: string[] = ['code', 'name', 'length', 'width', 'action'];
  dataSource: any;
  data: Ship[] = [];
  filteredData?: Ship[];

  pageSize = 5;
  pageIndex = 0;

  @ViewChild(MatPaginator)
  paginator: any = {};

  @ViewChild(MatSort) sortData: any = {};

  constructor(
    private dialog: MatDialog,
    private shipService: ShipService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.shipService.getShips().subscribe((data: Ship[]) => {
      this.data = data;
      this.dataSource = new MatTableDataSource(data);


      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sortData;
    });
  }


  createOrUpdate(data?: Ship): void {
    const dialogRef = this.dialog.open(ShipFormComponent, {
      width: '400px',
      data,
    });

    dialogRef.afterClosed().subscribe((result: Ship) => {
      if (!result) {
        return;
      }

      if (data) {
        this.updateShip(result);
      } else {
        this.addShip(result);
      }

    });
  }

  showAlert(msg: string): void {
    this._snackBar.open('Alert', msg, {
      duration: 3000,
    });
  }

  addShip(data: Ship): void {
    this.shipService.createShip(data).subscribe((res: Ship) => {
      this.data.push(res);
      this.handlePage();
      this.showAlert('Created Sucessfully');
    });
  }

  updateShip(data: Ship): void {
    this.shipService.updateShip(data).subscribe((res: Ship) => {
      const replaceIndex = this.data?.findIndex((x) => x.id == res.id);
      if (replaceIndex > -1 && replaceIndex < this.data.length)
        this.data[replaceIndex] = res;

      this.dataSource = this.data;
      this.showAlert('Updated Sucessfully');
    });
  }

  delete(id: number) {
    if(confirm('Are you sure want to delete')){
      this.shipService.deleteShip(id).subscribe(() =>{
        this.data = [...this.data.filter((x:Ship)=>x.id!==id)];
      });
      this.dataSource = this.data;
      this.showAlert('Deleted Successfully');
    }
    
  }

  public handlePage(e?: any): void {
    const currentPage = e?.pageIndex || this.pageIndex;
    this.pageSize = e?.pageSize || this.pageSize;
 
    const end = (currentPage + 1) * this.pageSize;
    const start = currentPage * this.pageSize;
    const data = this.data.slice(start, end);
    this.dataSource = data;
  }

  filter(searchTxt: any): void {
    if (!searchTxt) {
      this.dataSource.data = this.data;
      return;
    }

    this.filteredData = this.data?.filter(
      (ship) =>
        ship.name.indexOf(searchTxt) > -1 || ship.code.indexOf(searchTxt) > -1
    );
    this.dataSource.data = this.filteredData;
  }
}
