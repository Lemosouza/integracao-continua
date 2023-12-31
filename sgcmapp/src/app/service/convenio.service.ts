import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Convenio } from '../model/convenio';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConvenioService implements IService<Convenio> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/convenio/';

  get(termoBusca?: string | undefined): Observable<Convenio[]> {
    let url = this.apiUrl;
    if (termoBusca) {
      url += 'busca/' + termoBusca;
    }
    return this.http.get<Convenio[]>(url);
  }

  getById(id: number): Observable<Convenio> {
    return this.http.get<Convenio>(this.apiUrl + id);
  }

  insert(objeto: Convenio): Observable<Convenio> {
    return this.http.post<Convenio>(this.apiUrl, objeto);
  }

  update(objeto: Convenio): Observable<Convenio> {
    return this.http.put<Convenio>(this.apiUrl, objeto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + id);
  }

}
