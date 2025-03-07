import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { MonsterCard } from '../models/models';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'pokemon-card-dialog',
  templateUrl: './pokemon-card-dialog.component.html',
  styleUrl: './pokemon-card-dialog.component.scss'
})
export class PokemonCardDialogComponent implements OnInit {
  pokemonCard: MonsterCard;
  name: string = "";
  imageSource: string = "";
  formEmitter: EventEmitter<any> = new EventEmitter();    
  wantsToAddCard: boolean = false;
  imageIsLoading: boolean = true; 

  constructor(@Inject(MAT_DIALOG_DATA) public data: { 
    pokemonCard: MonsterCard 
  }) {
    this.pokemonCard = data.pokemonCard;
  }

  ngOnInit(): void {
    this.name = this.pokemonCard.name;
    this.imageSource = this.pokemonCard.imageLinks;
  }

  public emit(): void {
    this.formEmitter.emit({ 
      add: this.wantsToAddCard
    });
  }

  public handleClose(): void {
    this.wantsToAddCard = false;
  }

  public addCard(): void {
    this.wantsToAddCard = true;

    this.emit();
  }

  afterImageLoad(): void {
    this.imageIsLoading = false;
  }
}
