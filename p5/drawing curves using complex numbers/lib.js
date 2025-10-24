// ----------- complex & integration ---------------------
class Complex {
  constructor(r, theta) {
    if (!Number.isFinite(r) || r < 0) {
      throw "Invalid complex detected!";
    }
    this.r = r;
    this.theta = theta;
    this.x = this.r * cos(this.theta);
    this.y = this.r * sin(this.theta);
  }
  add(other) {
    let newX = this.x + other.x;
    let newY = this.y + other.y;
    let r = Math.sqrt(newX * newX + newY * newY);
    let theta = Math.atan2(newY, newX);
    return new Complex(r, theta);
  }
  mult(other) {
    return new Complex(this.r * other.r, this.theta + other.theta);
  }
  scaleBy(c) {
    let sign = c >= 0 ? 1 : -1;
    return new Complex(Math.abs(c) * this.r, sign * this.theta);
  }
}

function integralComplex(a, b, f, acc) {
  let sumX = 0,
    sumY = 0;
  for (let t = a; t < b; t += acc) {
    let c = f(t);
    sumX += acc * c.x;
    sumY += acc * c.y;
  }
  let r = Math.sqrt(sumX * sumX + sumY * sumY);
  let theta = Math.atan2(sumY, sumX);
  return new Complex(r, theta);
}
function extractDarkPixels(img, threshold = 50) {
  img.loadPixels();
  let points = [];

  for (let y = 0; y < img.height; y++) {
    for (let x = 0; x < img.width; x++) {
      let idx = 4 * (x + y * img.width);
      let r = img.pixels[idx];
      let g = img.pixels[idx + 1];
      let b = img.pixels[idx + 2];

      let brightness = (r + g + b) / 3;

      if (brightness < threshold) {
        // normalize x and y to [-1, 1]
        let nx = map(x, 0, img.width, -1, 1);
        let ny = map(y, 0, img.height, -1, 1);
        points.push(createVector(nx, ny));
      }
    }
  }

  return points;
}
function fitSmoothFunction(points) {
  if (points.length < 2) throw "At least two points required.";

  // Compute cumulative distances (arc-length parameterization)
  let distances = [0];
  for (let i = 1; i < points.length; i++) {
    distances.push(distances[i - 1] + p5.Vector.dist(points[i], points[i - 1]));
  }
  let totalLength = distances[distances.length - 1];

  // Normalize distances to [0,1]
  let ts = distances.map(d => d / totalLength);

  // Prepare spline interpolation separately for x and y
  let splineX = new p5.Curve();
  let splineY = new p5.Curve();

  for (let i = 0; i < points.length; i++) {
    splineX.addPoint(ts[i], points[i].x);
    splineY.addPoint(ts[i], points[i].y);
  }

  splineX.computeTangents();
  splineY.computeTangents();

  // Return smooth interpolating function
  return function(t) {
    t = constrain(t, 0, 1);

    let x = splineX.valueAt(t);
    let y = splineY.valueAt(t);

    let r = sqrt(x * x + y * y);
    let theta = atan2(y, x);

    return new Complex(r, theta);
  };
}

// --- Helper cubic spline interpolation class ---
p5.Curve = class {
  constructor() {
    this.points = [];
  }

  addPoint(t, value) {
    this.points.push({ t, value, m: 0 });
    this.points.sort((a, b) => a.t - b.t);
  }

  computeTangents() {
    const n = this.points.length;
    for (let i = 1; i < n - 1; i++) {
      let p0 = this.points[i - 1];
      let p1 = this.points[i + 1];
      this.points[i].m = (p1.value - p0.value) / (p1.t - p0.t);
    }
    this.points[0].m = (this.points[1].value - this.points[0].value) / (this.points[1].t - this.points[0].t);
    this.points[n - 1].m = (this.points[n - 1].value - this.points[n - 2].value) / (this.points[n - 1].t - this.points[n - 2].t);
  }

  valueAt(t) {
    const points = this.points;
    let i = points.findIndex(p => p.t >= t);

    if (i <= 0) return points[0].value;
    if (i >= points.length) return points[points.length - 1].value;

    let p0 = points[i - 1];
    let p1 = points[i];

    let dt = p1.t - p0.t;
    let u = (t - p0.t) / dt;

    let h00 = (2 * u ** 3 - 3 * u ** 2 + 1);
    let h10 = (u ** 3 - 2 * u ** 2 + u);
    let h01 = (-2 * u ** 3 + 3 * u ** 2);
    let h11 = (u ** 3 - u ** 2);

    return (
      h00 * p0.value +
      h10 * dt * p0.m +
      h01 * p1.value +
      h11 * dt * p1.m
    );
  }
};