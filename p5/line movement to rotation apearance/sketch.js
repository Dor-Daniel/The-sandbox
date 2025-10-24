let r = 200;
let n = 10;
let dots = [];
let time = 1;
let step = 0.01;

function setup() {
  createCanvas(500, 500);

  let angle = PI / n;
  for (let i = 0; i < n; i++) {
    let x = r * cos(-angle * i);
    let y = r * sin(-angle * i);
    dots.push(createVector(x, y));
  }
}

function draw() {
  background(50);
  translate(width / 2, height / 2);

  drawLines();
  drawDots();

  time += step;
}

function drawDots() {
  stroke(255);
  strokeWeight(1);
  fill(0);
  for (let i = 0; i < n; i++) {
    circle(
      dots[i].x * cos(time + (i * PI) / n),
      dots[i].y * cos(time + (i * PI) / n),
      10
    );
  }
}
function drawLines() {
  stroke(200);
  strokeWeight(0.2);
  let angle = PI / n;
  for (let i = 0; i < n; i++) {
    let sx = r * cos(angle * i);
    let sy = r * sin(angle * i);
    let ex = -r * cos(angle * i);
    let ey = -r * sin(angle * i);
    line(sx, sy, ex, ey);
  }
}
