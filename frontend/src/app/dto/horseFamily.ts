export class HorseFamily {
  constructor(
    public id: number,
    public name: string,
    public birthday: string,
    public parent1: HorseFamily[],
    public parent2: HorseFamily[]){
  }
}
