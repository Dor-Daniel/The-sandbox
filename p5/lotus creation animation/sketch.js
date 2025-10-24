let freq1 = 0.5;
let freq2 = 2;
let amp;
let dot1Y;
let dot2X;
let trail = [];

function setup() {
  createCanvas(600, 600);
  amp = min(width, height) * 0.45;

  
}

function draw() {
  background(50);
  translate(width / 2, height / 2);
  drawAxes();
  updateDots();
  updateTrail();
  drawTrail();
  drawGuides();
  drawDots();
  drawIntersection();
}

function drawAxes() {
  stroke(255);
  line(-width / 2, 0, width / 2, 0);
  line(0, -height / 2, 0, height / 2);
}

function updateDots() {
  let t = millis() / 10000;
  dot1Y = amp * sin(TWO_PI * freq1 * t);
  dot2X = amp * sin(TWO_PI * freq2 * t);
}

function updateTrail() {
  trail.unshift(createVector(dot2X, dot1Y));
  if(trail.count > 10000){
    trail.pop();
  }
}

function drawTrail() {
  stroke(255,120,0);
  strokeWeight(2)
  noFill();
  beginShape();
  for (let v of trail) {
    vertex(v.x, v.y);
  }
  endShape();
}

function drawGuides() {
  stroke(255);
  strokeWeight(0.5);
  line(-width / 2, dot1Y, width / 2, dot1Y);
  line(dot2X, -height / 2, dot2X, height / 2);
}

function drawDots() {
  fill(255);
  noStroke();
  circle(0, dot1Y, 8);
  circle(dot2X, 0, 8);
}

function drawIntersection() {
  fill(255);
  circle(dot2X, dot1Y, 8);
}
