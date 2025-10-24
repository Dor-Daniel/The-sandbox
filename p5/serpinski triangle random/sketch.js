
let p1, p2, p3,p4,p;
function setup() {
  createCanvas(400, 400);
  p1 = [0,-height/2 +20];
  // p4 = [width/2 -20,-height/2 +20];
  p2 = [-width/2 +20,height/2 -20];
  p3 = [width/2 -20,height/2 -20];
  p = [0,0]
  background(20);
  translate(width/2, height/2);
  circle(p1[0], p1[1],10);
  circle(p2[0], p2[1],10);
  circle(p3[0], p3[1],10);
  // circle(p4[0], p4[1],10);
  circle(p[0],p[1],2)
}

function draw() {
  
  fill(255);
  stroke(255)
  translate(width/2, height/2);

  
  let choise =  random([p1,p2,p3]);
    
  let middle = [(p[0] + choise[0])/2, (p[1] + choise[1])/2]
  p = middle;
  circle(middle[0], middle[1],2)
  
}