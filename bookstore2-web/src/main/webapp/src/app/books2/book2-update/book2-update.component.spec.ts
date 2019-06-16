import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Book2UpdateComponent } from './book2-update.component';

describe('Book2UpdateComponent', () => {
  let component: Book2UpdateComponent;
  let fixture: ComponentFixture<Book2UpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Book2UpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Book2UpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
