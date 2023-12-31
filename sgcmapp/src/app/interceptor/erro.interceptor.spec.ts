import { TestBed } from '@angular/core/testing';

import { ErroInterceptor } from './erro.interceptor';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { LoginService } from '../service/login.service';

describe('ErroInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule,
      RouterTestingModule
    ],
    providers: [
      ErroInterceptor,
      LoginService
    ]
  }));

  it('should be created', () => {
    const interceptor: ErroInterceptor = TestBed.inject(ErroInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
