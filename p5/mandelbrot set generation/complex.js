class Complex {
  constructor(a, b) {
    this.a = a;
    this.b = b;
    this.r = sqrt(a * a + b * b);
    this.theta = atan2(b, a);
  }
  
  add(other) {
    return new Complex(this.a + other.a, this.b + other.b);
  }
  
  sub(other) {
    return new Complex(this.a - other.a, this.b - other.b);
  }
  
  scale(val) {
    return new Complex(this.a * val, this.b * val);
  }
  
  multiply(other) {
    return new Complex(
      this.a * other.a - this.b * other.b,
      this.a * other.b + this.b * other.a
    );
  }
  
  conjugate() {
    return new Complex(this.a, -this.b);
  }
  
  reciprocal() {
    if (this.a === 0 && this.b === 0) {
      throw "Division by 0";
    }
    return new Complex(this.a / (this.r * this.r), -this.b / (this.r * this.r));
  }
  
  div(other) {
    if (other.a === 0 && other.b === 0) {
      throw "Division by 0";
    }
    return this.multiply(other.reciprocal());
  }
  
  toThePowerOf(n) {
    let r = pow(this.r, n);
    let theta = n * this.theta;
    return new Complex(r * cos(theta), r * sin(theta));
  }
  
  show(col) {
    fill(col);
    noStroke();
    // Draw a small circle at (a, b)
    circle(this.a, this.b, 3);
  }
}