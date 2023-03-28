#type vertex
#version 330 core
//a stands for attribute
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;

// Saying we're gonna upload two variables- it makes no sense you'll get it later
uniform mat4 uProjection;
uniform mat4 uView;

//f stands for fragment (because it is going to the fragment shader)
out vec4 fColor;

void main()
{
    fColor = aColor;
    // gl_Postion is a variable that always exists that we need to define
    gl_Position = uProjection * uView * vec4(aPos, 1.0);
}

#type fragment
#version 330 core

uniform float uTime;

in vec4 fColor;

out vec4 color;

void main()
{
  float noise = fract(sin(dot(fColor.xy, vec2(12.989, 78.233))) * 43758.5453);
  color = fColor * noise;
}