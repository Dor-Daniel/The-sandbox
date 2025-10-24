let angle = 0;
let f = (x)=>{
  return 5000/x;
}
function setup() {
  createCanvas(600, 600);
}

function draw() {
  background(50);
  translate(width/2,height/2);

  
  angle = (angle + 0.005) % TWO_PI;
  drawAxis(angle);
  drawFunction();
  
}

function drawAxis(angle){
  angleMode(RADIANS);
  stroke(255,180,0)
  strokeWeight(3)
  line(-width/2,100,width/2,100);
  let x =- tan(angle) * (height/2-100);
  let negx = tan(PI + angle) * (height/2+100);
  
  line(x,-height/2,negx,height/2);
  
}

function drawFunction(){
  stroke(255);
  strokeWeight(0.2)
  let step = 10;
  for(let x = -width / 2-1000; x < width / 2+1000; x += step){
    line(x,100, f(x), f(x) / tan(angle) - 100)
  }
}