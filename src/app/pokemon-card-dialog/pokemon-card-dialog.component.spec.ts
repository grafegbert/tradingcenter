import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonCardDialogComponent } from './pokemon-card-dialog.component';

describe('PokemonCardDialogComponent', () => {
  let component: PokemonCardDialogComponent;
  let fixture: ComponentFixture<PokemonCardDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PokemonCardDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PokemonCardDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
