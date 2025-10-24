class Point {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }

  middle(other) {
    return new Point((this.x + other.x) / 2, (this.y + other.y) / 2);
  }

  equals(other) {
    return this.x == other.x && this.y == other.y;
  }

  static ZERO() {
    return new Point(0, 0);
  }

  isInside() {
    return this.x <= width && this.x >= 0 && this.y <= height && this.y >= 0;
  }

  moveToMiddle() {
    return new Point(this.x - width / 2, this.y - height / 2);
  }

  static IsPointXIdentical(p1, p2) {
    return p1.x == p2.x;
  }

  scale(val) {
    return new Point(val * this.x, val * this.y);
  }

  sub(other) {
    return new Point(this.x - other.x, this.y - other.y);
  }

  add(other) {
    return new Point(this.x + other.x, this.y + other.y);
  }
}

class Line{
  constructor(start, end){
    this.start = start;
    this.end = end;
    this.m = null;
    this.b = null;
    if(start.x != end.x){
      this.m = (start.y - end.y) / (start.x - end.x);
      this.b = start.y - start.x * this.m;
    }    
  }
  
  show(c){
    stroke(c);
    line(this.start.x, this.start.y, this.end.x, this.end.y);
  }
  showEntire(c){
    stroke(220,220,220);
    
    if(this.m == null){
      line(this.start.x, - height/2, this.start.x, height/2);
    }else{
      line(-width/2, -this.m * width/2 + this.b,
           width/2, this.m * width/2 + this.b)
    }
    
  }
  
  parrallalBy(val, dir){
    if(this.m != null){
      let alpha = PI/2 - atan(this.m);
      if(alpha === 0) {
        return new Line(new Point(0, this.b + dir*val),
                       new Point(1, this.b + dir*val))
      }
      let newB = this.b + dir * val  / sin(alpha);
      return new Line(new Point(0, newB), 
                      new Point(width/2, this.m * width /2 + newB));
    }else{
      return new Line(new Point(this.start.x + dir * val,0), 
                      new Point(this.start.x + dir * val,height/2));
    }
  }
}

class Axis{
  constructor(p1,p2){
    this.p1 = p1;
    this.p2 = p2;
    
    this.l1 = new Line(Point.ZERO(), p1);
    this.l2 = new Line(Point.ZERO(), p2);
    
    this.lines1 = [];
    this.lines2 = [];
    
    for(let i = 0; i <= 10; i++){
      this.lines1.push(this.l1.parrallalBy(width /20 * (10 - i) , 1))
      this.lines2.push(this.l2.parrallalBy(width /20 * (10 - i) , 1))
    }
    
    for(let i = 0; i < 10; i++){
      this.lines1.push(this.l1.parrallalBy(width /20 * (10 - i) , -1))
      this.lines2.push(this.l2.parrallalBy(width /20 * (10 - i) , -1))
    }
  }
  
  show(c){
  
    for(let l of this.lines1){
      l.showEntire(c);
    }
    for(let l of this.lines2){
      l.showEntire(c);
    }
    strokeWeight(5)
    this.l1.showEntire();
    this.l2.showEntire();
  }
}

class SMat{
  constructor(a,b,
              c,d){
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }
  
  MultV(v){
    return new Point(v.x * this.a + v.y * this.b,
                     v.x * this.c + v.y * this.d);
  }
  
  determinent(){
    return this.a * this.d - this.b * this.c;
  }
  scale(c){
    return new SMat(this.a * c, this.b*c, this.c *c, this.d *c);
  }
  inverse(){
    if(this.determinent != 0){
      let r = 1/this.determinent() ;
      return new SMat(this.d, -this.b, -this.c, this.a).scale(r);
    }
    else return null;
  }
  transpose(){
    return new SMat(this.a, this.c, this.b , this.d);
  }
}
