import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Books2Component } from './books2.component';

describe('Books2Component', () => {
  let component: Books2Component;
  let fixture: ComponentFixture<Books2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Books2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Books2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
