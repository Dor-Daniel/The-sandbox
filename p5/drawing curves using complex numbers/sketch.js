
let x = 0;
let wave = [];
let coefficients = [];
let num = 50;

let f; 


function setup() {
  createCanvas(600, 600);
  background(50);
}

function draw() {
  background(50);
  translate(width / 2, height / 2);

  let v = new Complex((1+sin(2*x))*100, 3*x);
  circle(0,0,2*((1+sin(2*x))*100))
  line(0,0,v.x,v.y)

  wave.unshift(v);

  stroke(255, 0, 220);
  noFill();
  beginShape();
  for (let p of wave) {
    vertex(p.x, p.y);
  }
  endShape();

  x += 0.01;

  if (wave.length > 10000) wave.pop();
}