import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Book2NewComponent } from './book2-new.component';

describe('Book2NewComponent', () => {
  let component: Book2NewComponent;
  let fixture: ComponentFixture<Book2NewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Book2NewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Book2NewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
