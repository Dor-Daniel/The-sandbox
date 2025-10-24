let deep = 8;
let tri;
let A, B, C;

function setup() {
  createCanvas(400, 400);
  background(220);
  
  A = new Point(width/2, 0);
  B = new Point(width, height);
  C = new Point(0, height);
  
  tri = new Triangle(A,B,C,deep);
}

function draw() {
  tri.show(0);
  noLoop();
}




class Point{
  constructor(x,y){
    this.x = x;
    this.y = y;
  }
  
  middle(other){
    return new Point((this.x + other.x) / 2, (this.y + other.y) / 2);
  }
}

class Triangle{
  constructor(A, B, C, deep){
    this.A = A;
    this.B = B;
    this.C = C;
    this.deep = deep;
    this.AB = A.middle(B);
    this.BC = B.middle(C);
    this.AC = A.middle(C);
  }
  
  show(c){
    fill(c);
    
    line(this.A.x, this.A.y, this.B.x, this.B.y);
    line(this.A.x, this.A.y, this.C.x, this.C.y);
    line(this.B.x, this.B.y, this.C.x, this.C.y);
    
    if(this.deep > 0){
      let T1 = new Triangle(this.A, this.AB, this.AC, this.deep - 1);
      let T2 = new Triangle(this.B, this.AB, this.BC, this.deep - 1);
      let T3 = new Triangle(this.C, this.AC, this.BC, this.deep - 1);
      
      T1.show(c);
      T2.show(c);
      T3.show(c);
    }
  }
}