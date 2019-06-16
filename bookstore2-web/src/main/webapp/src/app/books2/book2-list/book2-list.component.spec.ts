import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Book2ListComponent } from './book2-list.component';

describe('Book2ListComponent', () => {
  let component: Book2ListComponent;
  let fixture: ComponentFixture<Book2ListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Book2ListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Book2ListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
