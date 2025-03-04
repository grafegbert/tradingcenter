import { Component, Input } from '@angular/core';
import { MonsterCard } from '../models/models';
import { PokemonCardDialogComponent } from '../pokemon-card-dialog/pokemon-card-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'pokemon-card',
  templateUrl: './pokemon-card.component.html',
  styleUrl: './pokemon-card.component.scss'
})
export class PokemonCardComponent {
  @Input() cardData!: MonsterCard;

  constructor(private dialog: MatDialog, private snackBar: MatSnackBar) {}

  public openCardDialog(): void {
    const dialogRef = this.dialog.open(PokemonCardDialogComponent, { 
      data: {
        pokemonCard: this.cardData
      } });

      dialogRef.componentInstance.formEmitter.subscribe((res: any) => {
        if (res.add === true) {
          this.snackBar.open("Added to collection", "", {
            duration: 2000
          });

        } 

        dialogRef.close();
      })
  }
}
