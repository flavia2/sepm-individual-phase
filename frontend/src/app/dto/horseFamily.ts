export class HorseFamily {
  constructor(
    public id: number,
    public name: string,
    public birthday: string,
    public mother: HorseFamily[],
    public father: HorseFamily[]){
  }
}
