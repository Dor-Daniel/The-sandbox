let maxIterations = 300;

function setup() {
  createCanvas(600, 600);
  pixelDensity(1); 
  noLoop();        
}

function draw() {
  loadPixels();
  

  let minA = -2.5;
  let maxA = 1;
  let minB = -1;
  let maxB = 1;
  

  for (let x = 0; x < width; x++){
    for (let y = 0; y < height; y++){
      
      let a = map(x, 0, width, minA, maxA);
      let b = map(y, 0, height, minB, maxB);
      
      let c = new Complex(a, b);
      
      let z = new Complex(0, 0);
      let n = 0;
      
      while (n < maxIterations && z.r <= 2) {
        z = z.multiply(z).add(c);
        n++;
      }
      let bright = map(n, 0, maxIterations, 0, 255);
      if (n === maxIterations) {
        bright = 0;
      }

      let index = (x + y * width) * 4;
      pixels[index + 0] = bright;
      pixels[index + 1] = bright;
      pixels[index + 2] = bright;
      pixels[index + 3] = 255;
    }
  }
  
  updatePixels();
}
