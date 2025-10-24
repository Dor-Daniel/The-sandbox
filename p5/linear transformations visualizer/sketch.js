function setup() {
  createCanvas(800, 600);
  e1 = new Point(1,0);
  e2 = new Point(0,1);
  Mat = new SMat(1,0,-1,1);
  
  

}

function draw() {
  background(40);
  translate(width / 2, height / 2);
  let v1 = Mat.MultV(e1);
  let v2 = Mat.MultV(e2);
  axis = new Axis(v1, v2);
  axis.show([255, 0, 0]);
  
  textSize(40);
  fill(255);
  noStroke()

  // Render simulated LaTeX matrix as plain text
  text("Matrix: ",  - 375,  -235);

  textSize(90);
  text("[",  -250,   -220)

  textSize(30)
  text(`${Mat.a}  ${Mat.b}`,  -225, -250);
  text(`${Mat.c}  ${Mat.d}`,  -225,  -215);
  textSize(90);
  text("]", -165,  -220);
    

  noLoop();
}


