import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConvenioListComponent } from './convenio-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { ConvenioService } from 'src/app/service/convenio.service';
import { BarraComandosComponent } from '../barra-comandos/barra-comandos.component';

describe('ConvenioListComponent', () => {
  let component: ConvenioListComponent;
  let fixture: ComponentFixture<ConvenioListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule, FormsModule ],
      providers: [ ConvenioService ],
      declarations: [ ConvenioListComponent, BarraComandosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConvenioListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
