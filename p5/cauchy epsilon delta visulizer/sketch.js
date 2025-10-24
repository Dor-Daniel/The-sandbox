let epsilon = 1000;
let r = 10;
function setup() {
  createCanvas(600,600);

  
}

function draw() {
  if(r%10 == 0){
    background(40);
    translate(width/2, height/2)
    axis();
    displayF((x) => -25*atan(0.05*x))
    forEachEpsilon(epsilon,9,(x) => -25*atan(0.05*x))
    epsilon/=2;
    
    if(epsilon<=1){
      noLoop();
      return
    }
  }
  r++;
}

function axis(){
  let numOfLines = 20;
  let step = width / numOfLines;
  stroke(120);
  for(let i = 0; i < numOfLines; i++){
    line(-width/2 + i*step, -height/2,-width/2 + i*step, height/2)
    line(-width/2 , -height/2 + i*step,width/2 , -height/2 + i*step)
  }
  stroke(255);
  line(-width/2,0,width/2,0);
  line(0,-height/2,0,height/2);
  line(0,-height/2,10,-height/2+10);
  line(0,-height/2,-10,-height/2+10);
  line(width/2 -10, 10,width/2,0);
  line(width/2, 0 , width/2 -10, -10);
}

function displayF(f){
  
  noFill();
  beginShape()
  for(let i = -width/2; i < width/2; i+=4){
    let y = f(i);
    vertex(i,y);    
  }
  endShape()
}

function findDeltaByEpsilon(epsilon,a, f){
  let ya = f(a);
  for(let delta = width/2; delta >0; delta--){
    for(let dis = 0; dis < delta; dis++){
      let y1 = f(a+dis);
      let y2 = f(a-dis);
      if(!(Math.abs(y1-ya) < epsilon && Math.abs(y2-ya)<epsilon)){
        break;
      }
      if(dis == delta-1){
        return delta;
      }
    }
  }
  return null;
}

function forEachEpsilon(epsilon,a,f){
  let c = color('red')
  c.setAlpha(128);
  fill(c)
  noStroke()
  rect(-width/2,f(a)+epsilon,width,2*epsilon)
  let delta = findDeltaByEpsilon(epsilon, a, f);
  c = color('green')
  c.setAlpha(128);
  fill(c)
  rect(a-delta,-height/2,2*delta,height)
  
}