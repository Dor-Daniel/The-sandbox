


const G = 6.67e-3;     
const SOFTENING = 1.0; 
const TRAIL_MAX = 8000;
const ELASTICITY = 1.0; 

const initPos = { x: 0, y: -100 };

let b1 = { mass: 5000, r: 10, pos: { x: -100, y: 0 } };
let b2 = { mass: 4200, r: 10, pos: { x:  100, y: 0 } };
let b3 = { mass: 1000, r:  5, pos: { x: initPos.x, y: initPos.y }, vel: { x: 0.3, y:0.5 } };

let camScale = 1.0;
const MIN_SCALE = 0.25, MAX_SCALE = 4.0, ZOOM_STEP = 1.1;

let trail = [];

const DT = 20;

function setup() {
  createCanvas(400, 400);

  b1.pos = createVector(b1.pos.x, b1.pos.y);
  b2.pos = createVector(b2.pos.x, b2.pos.y);
  b3.pos = createVector(b3.pos.x, b3.pos.y);
  b3.vel = createVector(b3.vel.x, b3.vel.y);
}

function draw() {
  background(20);
  translate(width / 2, height / 2);
  scale(camScale);


  noStroke();
  fill(255, 0, 0);
  circle(b1.pos.x, -b1.pos.y, b1.r * 2);
  fill(0, 255, 0);
  circle(b2.pos.x, -b2.pos.y, b2.r * 2);


  let acc = createVector(0, 0);
  acc.add(accelFrom(b3.pos, b1));
  acc.add(accelFrom(b3.pos, b2));

  b3.vel.add(p5.Vector.mult(acc, DT));
  b3.pos.add(p5.Vector.mult(b3.vel, DT));

  resolveCollision(b3, b1);
  resolveCollision(b3, b2);


  fill(0, 0, 255);
  circle(b3.pos.x, -b3.pos.y, b3.r * 2);


  trail.unshift({ x: b3.pos.x, y: b3.pos.y });
  if (trail.length > TRAIL_MAX) trail.pop();

  noFill();
  stroke(20, 50, 180);
  strokeWeight(2 / camScale);
  beginShape();
  for (let pt of trail) vertex(pt.x, -pt.y);
  endShape();

  resetMatrix();
  fill(200);
  noStroke();
  text(`zoom: ${camScale.toFixed(2)}x`, 10, 20);
}

function accelFrom(pos, body) {
  const r = p5.Vector.sub(body.pos, pos);         
  const d2 = r.x * r.x + r.y * r.y;
  if (d2 === 0) return createVector(0, 0);       
  const inv = 1.0 / (d2 + SOFTENING * SOFTENING); 
  const rMag = Math.sqrt(d2);
  const rHat = p5.Vector.mult(r, 1 / rMag);       
  return p5.Vector.mult(rHat, G * body.mass * inv);
}

function resolveCollision(mover, other) {
  const n = p5.Vector.sub(mover.pos, other.pos);
  const d = n.mag();
  const minD = mover.r + other.r;

  if (d >= minD || d === 0) return;

  const nHat = p5.Vector.mult(n, 1 / d);
  const penetration = minD - d;
  mover.pos.add(p5.Vector.mult(nHat, penetration + 0.0001));

  const v = mover.vel.copy();
  const vnMag = v.dot(nHat);
  const vn = p5.Vector.mult(nHat, vnMag);
  const vt = p5.Vector.sub(v, vn);
  mover.vel.set(vt.add(p5.Vector.mult(vn, -ELASTICITY)));

  mover.vel.mult(0.999);
}
function keyPressed() {
  if (key === 'w' || key === 'W') {
    camScale = constrain(camScale * ZOOM_STEP, MIN_SCALE, MAX_SCALE);
  } else if (key === 's' || key === 'S') {
    camScale = constrain(camScale / ZOOM_STEP, MIN_SCALE, MAX_SCALE);
  } else if (key === 'r' || key === 'R') {
    b3.pos.set(initPos.x, initPos.y);
    b3.vel.set(0.1, 0.618);
    trail = [];
  }
}
