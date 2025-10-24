// class Cell{
//   constructor(x,y,col){
//     this.x = x;
//     this.y = y;
//     this.col = col;
//   }
// }
// let cellCount = 20;
// let cells = [];
// let cellX, cellY;

// let noise = [];

// function getColorByCoords(x, y){
//   if(noise[x] == undefined) {
//     noise[x] = random() * 255;
//   }
//   return noise[x];
// }
let dots = [];
let dotCount = 129;
let dotNoise = [];
let isInit = true;
// function initArr(s,e)
// {
//   if(s==0 && e == dotCount-1){
//     dotNoise[s] = -height/3;
//     dotNoise[e] = height/3;
//   }
//   if(e - s <= 1) return;
//   let m = ceil((s + e)/2);
//   dotNoise[m] = random(dotNoise[s], dotNoise[e]+0.001);
//   initArr(s,m); initArr(m, e);
// }

function getyByX(x){
  return dotNoise[x];
}
function setup() {
  createCanvas(400, 400);
  background(20);
  frameRate(1);

  // cellX = width / cellCount;
  // cellY = height / cellCount;
  // for(let i = 0; i < cellCount; i++){
  //   for(let j = 0; j < cellCount; j++){
  //     let x = i*cellX;
  //     let y = j*cellY;
  //     cells.push(new Cell(x, y, getColorByCoords(x, y)));
  //   }
  // }

}

function drawAxis(){
  stroke(255);
  strokeWeight(1);
  
  line(-width/2, 0, width/2,0);
  line(0,-height/2,0,height/2);
}
let iter = 0;
function draw() {
  fill(255);
  translate(width/2, height/2);
  if(iter == 0){
    dotNoise[iter] = random(-1,0)*height/2;
    dotNoise[dotCount - 1 - iter] = random(0,1)*height/2;
    circle(-width/2, dotNoise[iter], 2);
    circle(width/2, dotNoise[dotCount - 1 - iter], 2);
  }else{
    let r = pow(2,iter - 1);
    if(pow(2,iter + 1) >= (dotCount - 1)) isInit = false;
    for(let i = 0; i < r; i++){
      
      // 0, dotCount - 1 
      // (dotCount - 1) * (1/2) // r = 1
      // (dotCount - 1) * (1/4), (dotCount - 1) * (3/4) // r = 2
      // (dotCount - 1) * (1/8), (dotCount - 1) * (3/8), (dotCount - 1)*(5/8),(dotCount - 1)*(7/8) // r = 4
      
      let p = (2*i+1)*(dotCount - 1)/(2*r);
      let q = p + (dotCount - 1) / (2 * r);
      let t = p - (dotCount - 1) / (2 * r);
      dotNoise[p] = random(dotNoise[t], dotNoise[q]);
      circle(-width/2 + p * width/dotCount, dotNoise[p], 5);
    }
    
    
  }
  iter++;
   
  // for(let cell of cells){
  //   fill(cell.col);
  //   rect(cell.x, cell.y, cellX, cellY);
  // }
  drawAxis();
  

  if(!isInit){
    background(20);
    let dt = width / dotCount;
    for(let i = 0; i < dotCount; i++){
      dots.push({x: width/2 - dt * i, y: getyByX(i)});
    }
    noFill();
    stroke(180,120,30);
    strokeWeight(1);
    beginShape();

    for(let dot of dots){
      vertex(dot.x, dot.y);
    }
    endShape();
    noLoop();
  }
}