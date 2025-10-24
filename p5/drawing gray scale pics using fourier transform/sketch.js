let img;
let time = 0;
let points = [];
let coCount = 100;        
let maxWaveSize = 10000;  
let fk = [];              
let wave = [];
let MinSubsetSize = 2000;

function preload() {
  img = loadImage('test.png');
}

function setup() {
  createCanvas(800, 400);
  img.resize(200, 200);
  img.filter(GRAY);
  img.loadPixels();


  const blackPixels = [];
  for (let y = 0; y < img.height; y++) {
    for (let x = 0; x < img.width; x++) {
      const i = (y * img.width + x) * 4;
      const r = img.pixels[i], g = img.pixels[i + 1], b = img.pixels[i + 2];
      if (r < 100 && g < 100 && b < 100) {
        blackPixels.push(createVector(x, y));
      }
    }
  }
  


  const N = min(MinSubsetSize, blackPixels.length);
  if(N == 0) {noLoop(); return}
  shuffle(blackPixels, true);
  points = blackPixels.slice(0, N);

  console.log(points.length)
  let cx = 0, cy = 0;
  for (let p of points) {
    cx += p.x; cy += p.y;
  }
  cx /= N; cy /= N;
  points.sort((a, b) => {
    let angA = atan2(a.y - cy, a.x - cx);
    let angB = atan2(b.y - cy, b.x - cx);
    return angA - angB;
  });

    fk = computeFourierCoefficients(points, coCount);
}

function draw() {
  background(50);
  translate(100,100);
  image(img, 400, 0);

  
  time = (time + 0.01) % 1;

  let p = evaluateSeriesAt(fk, time);

  wave.unshift(p);
  if (wave.length > maxWaveSize) wave.pop();

  noFill();
  stroke(255);
  beginShape()
    for (let w of wave) {
      vertex(w.x , w.y);
    }
  endShape()
  

  fill('red');
  noStroke();
  circle(p.x , p.y, 4);
}

function computeFourierCoefficients(pts, K) {
  const N = pts.length;
  const invN = 1 / N;
  let coeffs = [];

  for (let k = -K; k <= K; k++) {
    let sumRe = 0, sumIm = 0;
    for (let j = 0; j < N; j++) {
      let angle = -TWO_PI * k * j / N;
      let c = cos(angle), s = sin(angle);
      let x = pts[j].x, y = pts[j].y;
      sumRe += x * c - y * s;
      sumIm += x * s + y * c;
    }
    coeffs.push({ re: sumRe * invN, im: sumIm * invN, index: k });

  }

  return coeffs;
}

function evaluateSeriesAt(fk, t) {
  let sumRe = 0, sumIm = 0;

  for (let f of fk) {
    let angle = TWO_PI * f.index * t;
    let c = cos(angle), s = sin(angle);
    stroke(100)
    noFill()
    
    line(sumRe,sumIm, sumRe + c * f.re - s * f.im, sumIm + c * f.im + s * f.re)
    circle(sumRe + c * f.re - s * f.im, sumIm + c * f.im + s * f.re, 2*sqrt(f.re * f.re + f.im * f.im));
    sumRe +=  c * f.re - s * f.im;
    sumIm +=  c * f.im + s * f.re;
    
  }
  return { x: sumRe, y: sumIm };
}


