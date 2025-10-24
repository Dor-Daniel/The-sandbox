let img;
let canvasSize = 500;
let margin = 10;
let imgPixels = [];
let nails = [];
let nailCount = 250;
let nailSize = 1;
let lineIndex = [];
let maxLines = 10000;
let imgPath = "test.jpg"

let steps = 100; 
let bright = 10; 

let nailsGraphics; 

function preload() {
  img = loadImage("test.jpg");
}

function setup() {
  createCanvas(canvasSize * 2, canvasSize);
  
  nailsGraphics = createGraphics(canvasSize, canvasSize);
  
  img.resize(canvasSize, canvasSize);
  img.filter(GRAY);
  img.loadPixels();
  imgPixels = img.pixels.slice();

  const halfCanvas = canvasSize / 2;
  const angleStep = TWO_PI / nailCount;
  const r = halfCanvas - margin;
  for (let i = 0; i < nailCount; i++) {
    let angle = angleStep * i;
    let x = halfCanvas + r * cos(angle);
    let y = halfCanvas + r * sin(angle);
    nails.push(createVector(x, y));
  }

  nailsGraphics.clear();
  nailsGraphics.noFill();
  nailsGraphics.stroke(0, 30);
  nailsGraphics.strokeWeight(0.5);
  for (let i = 0; i < nailCount; i++) {
    nailsGraphics.ellipse(nails[i].x, nails[i].y, nailSize, nailSize);
  }
  
  lineIndex.push(floor(random(nailCount)));
}

function draw() {
  background(255);
  //right.
  image(nailsGraphics, 0, 0);
  image(img, canvasSize, 0);
  

  stroke(0, 30);
  strokeWeight(0.5);
  for (let i = 1; i < lineIndex.length; i++) {
    let n1 = nails[lineIndex[i - 1]];
    let n2 = nails[lineIndex[i]];
    line(n1.x, n1.y, n2.x, n2.y);
  }
  
  if (lineIndex.length >= maxLines) {
    print("Max Lines Reached");
    noLoop();
    return;
  }
  let currentIndex = lineIndex[lineIndex.length - 1];
  let currentNail = nails[currentIndex];
  
  let bestCandidate = null;
  let highestContrast = -1;
  let bestSamples = null;
  let invSteps = 1 / steps;
  

  for (let j = 0; j < nailCount; j++) {
    if (j === currentIndex) continue;
    let candidate = nails[j];
    let dx = candidate.x - currentNail.x;
    let dy = candidate.y - currentNail.y;
    let contrastSum = 0;
    let sampleIndices = new Array(steps);
    
    for (let k = 0; k < steps; k++) {
      let t = k * invSteps;
      let x = floor(currentNail.x + dx * t);
      let y = floor(currentNail.y + dy * t);
      // Check if the coordinates are within the image bounds.
      if (x < 0 || x >= canvasSize || y < 0 || y >= canvasSize) {
        sampleIndices[k] = -1;
        continue;
      }
      let pixIndex = ((y * canvasSize) + x) << 2;
      sampleIndices[k] = pixIndex;
      contrastSum +=(255 - imgPixels[pixIndex]);
    }
    let contrast = contrastSum * invSteps;
    if (contrast > highestContrast) {
      highestContrast = contrast;
      bestCandidate = j;
      bestSamples = sampleIndices;
    }
  }
  
  if (bestCandidate === null) {
    bestCandidate = floor(random(nailCount));
    print("Choosing random next index");
  }

  for (let k = 0; k < steps; k++) {
    let pixIndex = bestSamples[k];
    if (pixIndex < 0 || pixIndex >= imgPixels.length - 3) continue;
    if (imgPixels[pixIndex] < 255 - bright) {
      let newValue = imgPixels[pixIndex] + bright;
      imgPixels[pixIndex] = newValue;
      imgPixels[pixIndex + 1] = newValue;
      imgPixels[pixIndex + 2] = newValue;
    }
  }
  img.pixels.set(imgPixels);
  img.updatePixels();
  

  lineIndex.push(bestCandidate);
}
