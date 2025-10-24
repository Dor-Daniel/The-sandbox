let b1;
let start_loc = { x: -55, y: 0 };
let b_speed   = { x: 10, y: 10.1 };
let a = 100, b = 75, m = 0, n = 0;
let wave = [];
let waveCount = 10000;

function setup() {
  createCanvas(400, 400);
  b1 = new Ball(start_loc, b_speed);
}

function draw() {
  background(50);
  translate(width / 2, height / 2);


  noFill();
  stroke(255);
  strokeWeight(2);
  ellipse(m, n, 2 * a, 2 * b);

  b1.move();
  b1.display();
  
 
  noFill()
  strokeWeight(0.1);
  stroke(255);
  wave.unshift({x:b1.curr_loc.x, y:b1.curr_loc.y});
  if(wave.length > waveCount){
    wave.pop();
  }
  beginShape();
  for(let w of wave){
    vertex(w.x, w.y);
  }
  endShape();
}


class Ball {
  constructor(center, speed) {
    this.curr_loc     = { x: center.x, y: center.y };
    this.speed        = { x: speed.x, y: speed.y };
    this.trail        = [];
    this.trail_length = 15;
  }

  move() {
    this.checkBoundaries();
    this.curr_loc.x += this.speed.x;
    this.curr_loc.y += this.speed.y;

    this.trail.unshift({ x: this.curr_loc.x, y: this.curr_loc.y });
    if (this.trail.length > this.trail_length) {
      this.trail.pop();
    }
  }

  checkBoundaries() {
    const nextX = this.curr_loc.x + this.speed.x;
    const nextY = this.curr_loc.y + this.speed.y;
    const inside = 
      pow(nextX - m, 2) / sq(a) +
      pow(nextY - n, 2) / sq(b) <= 1;

    if (!inside) {

      const nx = (this.curr_loc.x - m) / (a * a);
      const ny = (this.curr_loc.y - n) / (b * b);


      const vdn    = this.speed.x * nx + this.speed.y * ny;
      const nnorm2 = nx * nx + ny * ny;

      this.speed.x = this.speed.x - 2 * vdn / nnorm2 * nx;
      this.speed.y = this.speed.y - 2 * vdn / nnorm2 * ny;
    }
  }

  display() {
    noStroke();
    for (let i = this.trail.length - 1; i >= 0; i--) {
      const pos   = this.trail[i];
      const sz    = map(i - 1, 0, this.trail.length - 1, 5, 1);
      const alpha = map(i,     0, this.trail.length - 1, 255, 50);
      fill(255, alpha);
      circle(pos.x, pos.y, sz);
    }
    stroke(255);
    strokeWeight(1);
    fill(255);
    circle(this.curr_loc.x, this.curr_loc.y, 5);
  }
}
