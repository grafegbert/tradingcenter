import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from "@angular/material/icon";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
  selector: 'pkmn-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss'
})
export class FooterComponent implements OnInit {
  constructor(private iconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer) {}
  ngOnInit(): void {
    // this.iconRegistry.addSvgIcon(
    //   'x-twitter',
    //   this.domSanitizer.bypassSecurityTrustResourceUrl('../assets/images/x-twitter-svg.svg')
    // );
  }
}
