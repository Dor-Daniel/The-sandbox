let bx = 200;
let x = 100;
let bspeed = -0.05;  
let speed = 0;
let mass = 1;
let bmass = 100; 
const smallWidth = 20;
const bigWidth = 40;
const canvasWidth = 600;

let counter = 0;

function setup() {
  createCanvas(canvasWidth, 600);
  frameRate(240);
}

function draw() {
  background(40);

  textSize(32);
  fill(255);
  stroke(0);
  strokeWeight(4);
  text('Collisions: '+ counter, 50, 50);

  let substeps = 20;
  let dt = deltaTime / substeps; 

  for (let i = 0; i < substeps; i++) {
    x += speed * dt;
    bx += bspeed * dt;


    if (x < bx) {

      if (x + smallWidth >= bx) {
        let v1 = speed;
        let v2 = bspeed;
        let newV1 = (2 * bmass * v2 + v1 * (mass - bmass)) / (mass + bmass);
        let newV2 = (2 * mass * v1 + v2 * (bmass - mass)) / (mass + bmass);
        speed = newV1;
        bspeed = newV2;
        x = bx - smallWidth;
        counter ++; 
      }
    } else { 
      if (bx + bigWidth >= x) {
        let v1 = speed;
        let v2 = bspeed;
        let newV1 = (2 * bmass * v2 + v1 * (mass - bmass)) / (mass + bmass);
        let newV2 = (2 * mass * v1 + v2 * (bmass - mass)) / (mass + bmass);
        speed = newV1;
        bspeed = newV2;
        bx = x - bigWidth;
        counter ++;
      }
    }

    if (x < 0) {
      x = 0;
      speed *= -1;
      counter++;
    } 

    if (bx < 0) {
      bx = 0;
      bspeed *= -1;
    } 
  }

  fill(255);
  noStroke();
  square(bx, height / 2 - 10, bigWidth); 
  square(x, height / 2, smallWidth);       
}
