import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { map } from 'rxjs';
import { SearchServiceService } from '../search-service.service';

@Component({
  selector: 'toolbar',
  templateUrl: './toolbar.component.html',
  styleUrl: './toolbar.component.scss'
})
export class ToolbarComponent implements OnInit {
  searchControl: FormControl = new FormControl();
  defaultPlaceholder: string = "Look for a card";
  currentSearchValue: string = this.defaultPlaceholder;
  noMatchingItemsFound: boolean = false; 
  userIsAuthenticated: boolean = false; 

  constructor(private searchService: SearchServiceService, private changeDetector: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.searchControl.valueChanges.subscribe((searchValue) => {
        //this.currentSearchValue = searchValue;
        this.searchService.pushUserInput(searchValue);
      });

    this.changeDetector.detectChanges();
  }

  resetPlaceholder(): void {
    this.currentSearchValue = this.defaultPlaceholder;
  }
}
