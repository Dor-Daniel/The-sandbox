let ratio = 1;
let rs = [1,1,2,3,5,8,13,21,34,55,79,134];
let thetas = [0,0,0,0,0,0,0,0,0,0,0,0];
let thetasStep = [1.34,0.79,0.55,0.34,0.21,0.13,0.08,0.05,0.03,0.02,0.01,0.01];
let wave = [];
let maxWave = 4000;
function setup() {
  createCanvas(600, 600);
  frameRate(240)
}

function draw() {
  background(40);
  translate(width/2, height/2);
  
  let x = 0, y = 0, oldx = 0, oldy = 0;
  let n = rs.length;
  
  for (let i = 0; i< n; i++){

    x = oldx + rs[i] * cos(thetas[i]);
    y = oldy + rs[i] * sin(thetas[i]);
    stroke(100,50,0);
    circle(oldx,oldy,2*rs[i])
    stroke(255);
    line(oldx,oldy,x,y);
    circle(x, y, 5);
    thetas[i] += thetasStep[i];
    oldx = x;
    oldy = y;
  }
  
  wave.unshift(createVector(x,y));
  
  noFill();
  beginShape();
  for(let w of wave){
    vertex(w.x, w.y);
  }
  endShape();
  
  if(wave.length > maxWave){
    wave.pop();
  }
}
