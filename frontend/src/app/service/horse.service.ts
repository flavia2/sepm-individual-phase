import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Horse} from '../dto/horse';
import {environment} from 'src/environments/environment';

const baseUri = environment.backendUrl + '/horses';
const httpOptions = {
  headers: new HttpHeaders({
    // eslint-disable-next-line @typescript-eslint/naming-convention
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class HorseService {
  constructor(private httpClient: HttpClient) {
  }

  getHorseById(id: number): Observable<Horse> {
    console.log('Load horse details for ' + id);
    return this.httpClient.get<Horse>(baseUri + '/' + id);
  }

  getAllHorses(): Observable<Horse[]> {
    console.log('Load all horses');
    return this.httpClient.get<Horse[]>(baseUri);
  }

  createHorse(horse: Horse): Observable<Horse> {
    console.log('Create new horse', horse);
    return this.httpClient.post<Horse>(baseUri, horse, httpOptions);
  }

  editHorse(horse: Horse): Observable<Horse> {
    console.log('Edit horse', horse);
    return this.httpClient.put<Horse>(baseUri + '/' + horse.id, horse, httpOptions);
  }
}
