let n = 200;
let r = 200;
let mul = 0;
let dmul = 0.01;
let dots = [];

function setup(){
  createCanvas(500, 500);
  
  let da = TWO_PI/n;
  for(let i = 0; i < n ; i++){
    dots.push({x: r * cos(i * da), y: r * sin(i * da)});
  }
}

function draw(){
  translate(width/2, height/2);
  background(50);
  
  for(let i = 0; i < n; i++){
    let d = dots[i];
    let j = (i * mul) % n;
    let dj = dots[floor(j)];
    fill(255);
    noStroke();
    circle(d.x, d.y, 2);
    noFill();
    stroke(220,120,50);
    line(d.x, d.y, dj.x, dj.y);
  }
  
  mul += dmul;
}
