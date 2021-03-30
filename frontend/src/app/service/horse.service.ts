import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Horse} from '../dto/horse';
import {environment} from 'src/environments/environment';
import {HorseFamily} from '../dto/horseFamily';
import {SearchedHorse} from '../dto/searchedHorse';

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

  deleteHorse(horse: Horse): Observable<Horse> {
    console.log('Delete horse', horse);
    return this.httpClient.delete<Horse>(baseUri + '/' + horse.id);
  }

  searchHorse(horse: SearchedHorse): Observable<Horse[]> {
    console.log('Search horse', horse);
    let params = new HttpParams();

    if (horse.name) {
      params = params.set('name', horse.name);
    }
    if (horse.description) {
      params = params.set('description', horse.description);
    }
    if (horse.birthday) {
      params = params.set('birthday', horse.birthday.toString());
    }
    if (horse.gender) {
      params = params.set('gender', horse.gender.toString());
    }
    if (horse.sport) {
      params = params.set('sport', horse.sport.toString());
    }
    return this.httpClient.get<Horse[]>(baseUri + '/search', {params});
  }

  getFamilyTree(id: number): Observable<HorseFamily[]> {
    console.log('Get family tree of horse with id ' + id);
    return this.httpClient.get<HorseFamily[]>(baseUri + '/family/' + id);
  }

  getFamilyTreeWithGens(id: number, gens: number): Observable<HorseFamily[]> {
    console.log('Get family tree with ' + gens + 'generations of horse with id ' + id);
    let params = new HttpParams();
    params = params.set('generations', gens.toString());
    return this.httpClient.get<HorseFamily[]>(baseUri + '/family/' + id, {params});
  }
}
