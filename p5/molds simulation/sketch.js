let molds = [];
let num = 4000;
let d;

function setup() {
  createCanvas(800, 600);
  angleMode(DEGREES);
  d= pixelDensity();
  for(let i = 0; i < num; i++){
    molds[i] = new Mold();
  }
  
}

function draw() {
  background(0,5);
  loadPixels();
  molds.map((m) =>{
    m.update();
    m.display();
  })

  
}

class Mold {
  constructor(xConst = width, yConst=height ){
    this.x = random(width);
    this.y = random(height); 
    this.r = 0.5;
    
    this.heading = random(360);
    this.vx = cos(this.heading);
    this.vy = sin(this.heading);
    
    this.sensors = [
      createVector(0, 0),
      createVector(0, 0),
      createVector(0, 0)
    ];
    this.sensorAngle = 45;
    this.sensorDist = 10;
    this.rotAngle = 45;
  }
  
  update(){
    this.vx = cos(this.heading);
    this.vy = sin(this.heading);
    
    this.x = (this.x + this.vx + width) % width;
    this.y = (this.y + this.vy + height) % height;
    
    for(let i = 0; i < 3; i++){
      this.sensors[i].x = this.x + 
      this.sensorDist * cos(this.heading + (i-1)*this.sensorAngle);
      this.sensors[i].x = (this.sensors[i].x + width) % width;
      this.sensors[i].y = this.y + 
      this.sensorDist * sin(this.heading + (i-1)*this.sensorAngle);
      this.sensors[i].y = (this.sensors[i].y + height) % height;
    }

    let indexes = [];
    for (let i = 0; i < 3; i++) {
      let ix = floor(this.sensors[i].x * d);
      let iy = floor(this.sensors[i].y * d);
      let index = 4 * (iy * width * d + ix);
      indexes[i] = pixels[index]; 
    }
    let [left, front, right] = indexes;

    if(front > left && front > right){
      this.heading += 0;
    } else if (front < left && front < right){
      if(random(1) < 0.5){
        this.heading += this.rotAngle;
      }
    }else if (left > right){
      this.heading -= this.rotAngle;
    }else if (right > left){
      this.heading += this.rotAngle;
      
    }
    

  }
  
  display(){
    noStroke();
    fill(255);
    ellipse(this.x, this.y ,this.r * 2, this.r * 2);
    
  }
}