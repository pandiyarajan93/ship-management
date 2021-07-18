import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
})
export class SearchBarComponent {
  @Output() filterEvent = new EventEmitter<string>();

  filter(searchTxt: string): void {
    console.log(searchTxt);
    this.filterEvent.emit(searchTxt);
  }
}
