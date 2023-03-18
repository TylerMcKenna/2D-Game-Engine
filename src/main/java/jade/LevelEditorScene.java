package jade;

import org.lwjgl.opengl.GL20;

import java.awt.event.KeyEvent;

import static org.lwjgl.opengl.GL20.*;

public class LevelEditorScene extends Scene
{
    //src stands for source
    private String vertexShaderSrc = "#version 330 core\n" +
            "//a stands for attribute\n" +
            "layout (location=0) in vec3 aPos;\n" +
            "layout (location=1) in vec4 aColor;\n" +
            "\n" +
            "//f stands for fragment (because it is going to the fragment shader)\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    fColor = aColor;\n" +
            "    // gl_Postion is a variable that always exists that we need to define\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "}";

    private String fragmentShaderSrc =  "#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    color = fColor;\n" +
            "}";

    private int vertexID, fragmentID, shaderProgram;

    private float[] vertexArray =
    {
        // position              // color
        0.5f, -0.5f, 0.0f,       1.0f, 0.0f, 0.0f, 1.0f, // Bottom right 0
       -0.5f,  0.5f, 0.0f,       0.0f, 1.0f, 0.0f, 1.0f, // Top left     1
        0.5f,  0.5f, 0.0f,       0.0f, 0.0f, 1.0f, 1.0f, // Top right    2
       -0.5f, -0.5f, 0.0f,       1.0f, 1.0f, 0.0f, 1.0f  // Bottom left  3
    };

    private int[] elementArray =
    {
        /*
                X        X


                X        X
         */
        2, 1, 0, // Top right triangle
        0, 1, 3  // Bottom left triangle
    };

    public LevelEditorScene()
    {

    }

    @Override
    public void init()
    {
        // ============================================
        // Compile and link shaders
        // ============================================

        // First load and compile the vertex shader
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        // Pass the shader source code to the GPU
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);

        // Check for errors in compilation
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE)
        {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("Error: 'defaultShader.glsl'\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        // Load and compile the fragment shader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        // Pass the shader source code to the GPU
        glShaderSource(fragmentID, fragmentShaderSrc);
        glCompileShader(fragmentID);

        // Check for errors in compilation
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE)
        {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("Error: 'defaultShader.glsl'\n\tFragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

        // Link shaders and check for errors
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        // Check for linking errors
        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (success == GL_FALSE)
        {
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("Error: 'defaultShader.glsl'\n\tLinking of shaders failed.");
            System.out.println(glGetProgramInfoLog(shaderProgram, len));
            assert false : "";
        }
    }

    @Override
    public void update(float dt)
    {

    }
}
