vec3 crossProduct(vec3 v, vec3 u){
    vec3 a = vec3(1., 0., 0.) * (v.y * u.z - u.y * v.z);
    vec3 b = vec3(0., 1., 0.) * (v.x * u.z - u.x * v.z);
    vec3 c = vec3(0., 0., 1.) * (v.x * u.y - u.x * v.y);
    return a - b + c;
}

vec3 findAnyNormalTo(vec3 p){
    if(p.x != 0.){
        if(p.y != 0.){
            return normalize(vec3(-1., p.x / p.y, 0.));
        }else{
            return vec3(0., 1., 0.);
        }
    }else{
        return vec3(1., 0. ,0.);
    }
}

struct Plane{
    vec3 position; 
    vec3 normal; 
    float xRange; 
    float yRange;
};

void getBasisByPlane(in Plane plane, out vec3 v, out vec3 u){
    u = findAnyNormalTo(plane.normal);
    v = crossProduct(u, plane.normal);
    v = normalize(v);
}

vec2 orthographicOntoPlane(Plane plane, vec3 p){
    vec3 q = p - plane.normal * (dot(plane.normal, p - plane.position));
    vec3 u, v;
    getBasisByPlane(plane, u, v);
    return vec2(dot(u, q - plane.position), dot(v, q -plane.position));
}

void drawWireframe(in vec2 vertices[8], in ivec2 edges[12], in vec2 uv ,out float d){
    float alpha = 2.;
    float thickness = 0.003;
    float best = 0.0;                 // start at 0, accumulate with max

    for (int i = 0; i < 12; i++){
        vec2 p0 = vertices[edges[i].x];
        vec2 p1 = vertices[edges[i].y];
        vec2 dir = p1 - p0;
        float len2 = dot(dir, dir);
        if (len2 > 0.0) {
            float t = clamp(dot(uv - p0, dir) / len2, 0.0, 1.0);
            vec2 c = p0 + t * dir;
            float l = length(uv - c);
            float w = alpha * fwidth(l);
            // 1 at line center, 0 outside
            float a = 1.0 - smoothstep(thickness - w, thickness + w, l);
            best = max(best, a);     
        }
    }
    d = best;
}

vec3 MultByMatrix(vec3 x, vec3 col1, vec3 col2, vec3 col3){return col1 * x.x + col2 * x.y + col3 * x.z;}
vec3 rotateAlongXAxis(vec3 v, float angle){
    return MultByMatrix(
        v, 
        vec3(1., 0., 0.), 
        vec3(0., cos(angle), sin(angle)),
        vec3(0., -sin(angle), cos(angle)));
        }
vec3 rotateAlongYAxis(vec3 v, float angle){
    return MultByMatrix(
        v, 
        vec3(cos(angle), 0., -sin(angle)), 
        vec3(0., 1., 0.),
        vec3(sin(angle), 0.,  cos(angle))
        );
        }
vec3 rotateAlongZAxis(vec3 v, float angle){
    return MultByMatrix(
        v, 
        vec3(cos(angle), sin(angle), 0.), 
        vec3(-sin(angle), cos(angle), 0.),
        vec3(0., 0., 1.));
        }



float Min(float a, float b){return a < b ? a : b;}
float Max(float a, float b){return a < b ? b : a;}

vec3[8] rotateArrayAlongXAxis(int len, vec3[8] arr, float angle){
    vec3[8] retArr;
    for(int i = 0; i < len; i++){
        retArr[i] = rotateAlongXAxis(arr[i], angle);
        }
    return retArr;
}

vec3[8] rotateArrayAlongYAxis(int len, vec3[8] arr, float angle){
    vec3[8] retArr;
    for(int i = 0; i < len; i++){
        retArr[i] = rotateAlongYAxis(arr[i], angle);
        }
    return retArr;
}

vec3[8] rotateArrayAlongZAxis(int len, vec3[8] arr, float angle){
    vec3[8] retArr;
    for(int i = 0; i < len; i++){
        retArr[i] = rotateAlongZAxis(arr[i], angle);
        }
    return retArr;
}

vec2[8] projectArrayOnPlane(int len, vec3[8] arr, Plane plane){
    vec2[8] retArr;
    for(int i = 0; i < len; i++){
        retArr[i] = orthographicOntoPlane(plane, arr[i]);
        }
    return retArr;
}

float MinFromArrayToPoint(vec2[8] arr, vec2 p){
    float min = distance(arr[0], p);
    for(int i = 0; i < 8; i++){
        float l = distance(arr[i], p);
        if(l < min){
            min = l;
        }
    }
    return min;
}

void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    // Normalized pixel coordinates (from 0 to 1)
    vec2 uv = 2. * (fragCoord/iResolution.xy -.5);
    uv.x *= iResolution.x / iResolution.y;
    vec3 col = vec3( 1., uv.y, 0.);
    
    // <---- screen setup ---->
    // Screen is XZ plane with size a * b
    vec3 screenPos = vec3(-0., -0., -0.);
    vec3 screenNorm = normalize(vec3(1. , 1., -0.5));
    float a = 2.;
    float b = 2.;
    Plane screen = Plane(screenPos, screenNorm, a, b);
    // Scale uv coords to match the screen plane size
    uv *= vec2(a, b);

    // Define vertices of a 3D-cube
    vec3[] verts = vec3[8] (
        vec3( 1.,  1.,  1.),
        vec3( 1.,  1., -1.),
        vec3( 1., -1.,  1.),
        vec3( 1., -1., -1.),
        vec3(-1.,  1.,  1.),
        vec3(-1.,  1., -1.),
        vec3(-1., -1.,  1.),
        vec3(-1., -1., -1.)
    );
    // Define edges of a 3D-cube
    ivec2[] edges = ivec2[12] (
        ivec2(0, 1), ivec2(0, 2), ivec2(1, 3), ivec2(2, 3),
        ivec2(0, 4), ivec2(1, 5), ivec2(4, 5), ivec2(4, 6),
        ivec2(3, 7), ivec2(2, 6), ivec2(6, 7), ivec2(5, 7)
    ); 

    
    vec3[8] rotatedXVerts = rotateArrayAlongZAxis(8, verts, iTime);
    vec3[8] rotatedXYVerts = rotateArrayAlongZAxis(8, rotatedXVerts, iTime);
    vec2[8] projectedVerts = projectArrayOnPlane(8, rotatedXYVerts, screen);

    float alpha;
    drawWireframe(projectedVerts, edges, uv, alpha);
    float d = MinFromArrayToPoint(projectedVerts, uv);
    col *= 1.- smoothstep(0.05, 0.0, d); 
    col = mix(col, vec3(0.0), alpha);  

    
    // Output to screen
    fragColor = vec4(col,1.0);
}