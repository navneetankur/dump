#version 330 core
out vec4 FragColor;
in vec2 fTex;
uniform sampler2D texture0;

void main()
{
    FragColor = vec4(1.0); // set alle 4 vector values to 1.0
    vec4 texColor = texture(texture0, fTex);
    if(texColor.a < 0.1)
        discard;
    FragColor = texColor;
}
