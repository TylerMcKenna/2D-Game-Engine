#type vertex
#version 330 core
//a stands for attribute
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;

// Saying we're gonna upload two variables- it makes not sense you'll get it later
uniform mat4 uProjection;
uniform mat4 uView;

//f stands for fragment (because it is going to the fragment shader)
out vec4 fColor;

void main()
{
    fColor = aColor;
    // gl_Postion is a variable that always exists that we need to define
    gl_Position = uProjection * uView * (aPos, 1.0);
}

#type fragment
#version 330 core

in vec4 fColor;

out vec4 color;

void main()
{
    color = fColor;
}