import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JuniorCardComponent } from './junior-card.component';

describe('JuniorCardComponent', () => {
  let component: JuniorCardComponent;
  let fixture: ComponentFixture<JuniorCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JuniorCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JuniorCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
