import { AfterViewInit, Component, Input, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry } from '@angular/material/icon';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  // copy = '';

  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    // iconRegistry.addSvgIcon(
    //   'facebook',
    //   sanitizer.bypassSecurityTrustResourceUrl('/assets/icons/facebook.svg')
    // );
    // iconRegistry.addSvgIcon(
    //   'twitter',
    //   sanitizer.bypassSecurityTrustResourceUrl('/assets/icons/twitter.svg')
    // );
    // iconRegistry.addSvgIcon(
    //   'linkedin',
    //   sanitizer.bypassSecurityTrustResourceUrl('/assets/icons/linkedin.svg')
    // );
  }
}
