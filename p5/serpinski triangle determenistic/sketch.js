let time = 0;
let radius = [50,20];
let wave = [];
let amplitude = [10,20,30,3,7,10,20,30,3,7];
let startDraw;

function setup() {
  createCanvas(800, 600);
  startDraw = 100;
}

function draw() {
  background(0);
  translate(200, 300);

  let x = [0];
  let y = [0];
  circle(0, 0, 2 * radius[0]);

  stroke(255);
  noFill();
  let n = radius.length;
  for (let i = 1; i < n; i++) {
    x[i] = radius[i - 1] * cos(amplitude[i - 1] * time) + x[i - 1];
    y[i] = radius[i - 1] * sin(amplitude[i - 1] * time) + y[i - 1];
    circle(x[i], y[i], 2 * radius[i]);
    line(x[i - 1], y[i - 1], x[i], y[i]);
  }

  n--;
  let x1 = radius[n] * cos(amplitude[n] * time) + x[n];
  let y1 = radius[n] * sin(amplitude[n] * time) + y[n];
  line(x[n], y[n], x1, y1);
  fill(255);
  circle(x1, y1, 5);

  line(x1, y1, startDraw, y1);
  circle(startDraw, y1, 5);

  wave.unshift(y1);

  noFill();
  beginShape();
  for (let i = 0; i < wave.length; i++) {
    vertex(startDraw + i, wave[i]);
  }
  endShape();
  if (wave.length > width) {
    wave.pop();
  }

  time += 0.01;
  if (time >= 2 * PI) {
    time -= 2 * PI;
  }
}
