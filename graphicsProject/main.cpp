#include <glad/glad.h>
#include <GLFW/glfw3.h>

#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <glm/gtc/type_ptr.hpp>
#include <glm/gtx/io.hpp>

#include <custom/shader_m.h>
#include <custom/camera.h>

#include <iostream>
#define STB_IMAGE_IMPLEMENTATION
#include "stb_image.h"
#include <stdio.h>
#include <stdlib.h>
#include <cstring>
#include<time.h>
#define BUFFER_SIZE 200
#define NO_TEXTURE_REGION 15
#define NO_BUTTONS (1 + 2+7 + 1+3+1+3+1+3 + 1+3+1+3+1+3 + 2+2+1)
//7 for isovalue
#define BUTTON_SIZE 0.0625
#define WIDTH_OF_CHAR 40
#define HEIGHT_OF_CHAR 40
#define START_CHAR 32
#define ROW_LENGTH 12
#define MENU_HEIGHT 0.0625
char buffer[BUFFER_SIZE];
int TextureRegionXSize;
int TextureRegionYSize;
int noOfChars;
struct TextureRegion {
    char name[20];
    int x;
    int y;
    int width;
    int height;
};

void save_off_file();
void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void processInput(GLFWwindow *window);
void mouse_button_callback(GLFWwindow* window, int button, int action, int mods);
void normalized_coordinates(int x, int y, double *normX, double *normY);
TextureRegion * load_texture_region();
void map_texture_to_button(const char * name, float button[4][5]);
void set_right_of(float right[4][5], float left[4][5]);
TextureRegion & get_texture_region(const char * name);
void printButton(float button[4][5]);
void place_at(float button[4][5], float x, float y, float width, float height);
float draw_text_at(float buttons[][4][5],char *text,float x, float y=1-MENU_HEIGHT, float height=MENU_HEIGHT);
void create_char(char c,float button[4][5]);
void click_button(float xpos);
void key_callback(GLFWwindow* window, int key, int scancode, int action, int mods);
void setup_ui();
void resend_data();
void character_callback(GLFWwindow* window, unsigned int codepoint);
void mouse_callback(GLFWwindow* window, double xpos, double ypos);
void scroll_callback(GLFWwindow* window, double xoffset, double yoffset);
float * dequeue(float queue[][3]);
void enqueue(float queue[][3], float x, float y, float z);
float get_max(float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8);
float get_min(float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8);
void handle_tetrahedron(float *v3, float *v4, float *v6, float *v7, float (*triangles)[3][3]);
int load_datafile(char const *offFile, void ** scalarField_vp, unsigned int *max_scalar);
void print(const char *str);
void load_seed_file(char * file);
// settings
unsigned int scr_width = 800;
unsigned int scr_height = 600;

// camera
Camera camera(glm::vec3(0.0f, 0.0f, 3.0f));
float lastX = scr_width / 2.0f;
float lastY = scr_height / 2.0f;
bool firstMouse = true;
// timing
float deltaTime = 0.0f;	
float lastFrame = 0.0f;

//globals
TextureRegion *textureRegions;
TextureRegion *fontRegionP;
float iso_value = 150.6;
int query_cell_x = -1;
int query_cell_y = -1;
int query_cell_z = -1;
unsigned int animation_multiplier = 50;
bool animate = false;
bool reading_query_cell = false;
bool reading_iso_value = false;
float (*b_iso_left)[5], (*b_iso_right)[5], (*b_iso_text)[4][5] ;
float (*b_query)[5], (*b_animation_left)[5];
float (*b_animation_right)[5], (*b_animation_play)[5];
float (*buttons)[4][5],(*b_query_text)[4][5] ,(*b_off_text)[4][5];
unsigned int VBO;
unsigned int buffer_counter = 0;
char const * OFF_FILENAME = "../data/nucleon_data.txt";
unsigned int triangle_counter = 0;
unsigned int q_begin = 0;
unsigned int q_end = 0;
unsigned int q_capacity;
unsigned int q_current = 0;
float (*queue)[3];
bool rightClickPressed = false;
bool firstRun = true;
float (*triangles)[3][3];
unsigned int (*tertrahedrons)[4][3];
bool query_found = false;
unsigned int size;
unsigned int borderCubeVAO, borderCubeVBO;
unsigned int modelVBO, modelVAO;
unsigned int (*seeds)[3];
unsigned int no_seeds = 0;
unsigned int drawingTriangles = 0;
bool use_query_cell = true;


int main(int argc, char *argv[])
{
    clock_t start = clock();
    char *seed_file = 0,*grid_file=0;
    if(argc > 3) {
        seed_file = argv[3]; 
    }
    if(argc > 2) {
        sscanf(argv[2],"%f",&iso_value);
    }
    if(argc > 1) {
        grid_file = argv[1];
    }
    if(seed_file) {
        load_seed_file(seed_file);
        use_query_cell = false;
    }



    textureRegions = load_texture_region();
    fontRegionP = &get_texture_region("font");

    void * scalarField_vp;
    unsigned int max_scalar = 0;
    if(grid_file)
        size = load_datafile(grid_file, &scalarField_vp, &max_scalar);
    else
        size = load_datafile(OFF_FILENAME, &scalarField_vp, &max_scalar);
    unsigned int (*scalarField)[size][size] = (unsigned int (*)[size][size])scalarField_vp;
    scalarField_vp = 0;
    float (*vertices)[size][size][7];
    vertices = (float (*)[size][size][7])malloc(sizeof(float)*7*size*size*size);
    unsigned int vertex_counter = 0;
    unsigned int (*marked)[size][size];
    for(unsigned int z=0;z<size;z++) {
        for(unsigned int y=0;y<size;y++) {
            for(unsigned int x=0;x<size;x++) {
                vertices[x][y][z][0] = x;
                vertices[x][y][z][1] = y;
                vertices[x][y][z][2] = z;
                vertices[x][y][z][3] = 0.0f;
                vertices[x][y][z][4] = 0.0f;
                vertices[x][y][z][5] = 0.0f;
                vertices[x][y][z][6] = scalarField[x][y][z];
            }
        }
    }

    triangles = (float (*)[3][3])malloc(sizeof(float)*(size-1)*(size-1)*(size-1)*6*2*3*3);
    //tertrahedrons = (unsigned int (*)[4][3])malloc(sizeof(unsigned int)*(size-1)*(size-1)*(size-1)*6*4*3);
    //find a query cell
    q_capacity = 3*(size-1)*(size-1)*(size-1);
    marked = (unsigned int (*)[size][size])malloc((size-1)*(size-1)*(size-1)*sizeof(unsigned int));
    queue = (float (*)[3])malloc(sizeof(float)*q_capacity);
    for(int i=0;i<no_seeds;i++) {
        enqueue(queue, seeds[i][0], seeds[i][1], seeds[i][2]);
    }
    // glfw: initialize and configure
    // ------------------------------
    glfwInit();
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    // glfw window creation
    // --------------------
    GLFWwindow* window = glfwCreateWindow(scr_width, scr_height, "Graphics Project", NULL, NULL);
    if (window == NULL)
    {
        std::cout << "Failed to create GLFW window" << std::endl;
        glfwTerminate();
        return -1;
    }
    glfwMakeContextCurrent(window);
    glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);
    glfwSetMouseButtonCallback(window, mouse_button_callback);
    glfwSetCharCallback(window, character_callback);
    glfwSetKeyCallback(window, key_callback);
    glfwSetCursorPosCallback(window, mouse_callback);
    glfwSetScrollCallback(window, scroll_callback);


    // tell GLFW to capture our mouse
    //glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

    // glad: load all OpenGL function pointers
    // ---------------------------------------
    if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
    {
        std::cout << "Failed to initialize GLAD" << std::endl;
        return -1;
    }

    // configure global opengl state
    // -----------------------------
    glEnable(GL_DEPTH_TEST);

    // build and compile our shader zprogram
    // ------------------------------------
    Shader uiShader("ui.vs", "ui.fs");
    Shader modelShader("main.vs", "main.fs");

    float size_f = (float)size;
    float vertices_b[][3] = {
        0, 0, 0,
        size_f-1, 0, 0,
        0, 0, 0,
        0, size_f-1, 0,
        0, size_f-1, 0,
        size_f-1, size_f-1, 0,
        size_f-1, size_f-1, 0,
        size_f-1,0,0,
        0, 0, size_f-1,
        size_f-1, 0, size_f-1,
        0, 0, size_f-1,
        0, size_f-1, size_f-1,
        0, size_f-1, size_f-1,
        size_f-1, size_f-1, size_f-1,
        size_f-1, size_f-1, size_f-1,
        size_f-1,0,size_f-1,

        0, 0, 0,
        0, 0, size_f-1,
        size_f-1, 0, 0,
        size_f-1, 0, size_f-1,
        0, size_f-1, 0,
        0, size_f-1, size_f-1,
        size_f-1, size_f-1, 0,
        size_f-1, size_f-1, size_f-1
    };

    //texture
    unsigned int texture;
    glGenTextures(1, &texture);
    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, texture);

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);	
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    /* glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST); */
    /* glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST); */

    int width, height, _;
    unsigned char *data = stbi_load("icons/project.png",&width,&height,&_,4);
    if(data) {
        //glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
        //glGenerateMipmap(GL_TEXTURE_2D);
    }
    else {
        std::cout << "Failed to load texture" << std::endl;
    }
    //stbi_image_free(data);
    free(data);

    // set up vertex data (and buffer(s)) and configure vertex attributes
    // ------------------------------------------------------------------
    /* unsigned int indices[] = { */
    /*     0,1,2, */
    /*     2,3,0, */
    /* }; */
    unsigned int (*indices)[6] = (unsigned int (*)[6])malloc(sizeof(unsigned int) * NO_BUTTONS * 6);
    for(int i=0; i<NO_BUTTONS; i++) {
        indices[i][0] = 0+i*4;
        indices[i][1] = 1+i*4;
        indices[i][2] = 2+i*4;

        indices[i][3] = 2+i*4;
        indices[i][4] = 3+i*4;
        indices[i][5] = 0+i*4;
    }
    buttons = (float (*)[4][5])malloc(sizeof(float) * 5 * 4 * NO_BUTTONS);//each button has 4 vertex and each vertex has 5 attributes xyzst

    for(int j=0;j<4;j++) {
        buttons[0][j][2] = 0.99;//z
    }
    for(int i=1;i<NO_BUTTONS;i++) {
        for(int j=0;j<4;j++) {
            buttons[i][j][2] = 0.8;//z
        }
    }
    
    setup_ui();
    glGenBuffers(1, &VBO);
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(float)*NO_BUTTONS*4*5, buttons, GL_STATIC_DRAW);

    unsigned int EBO;
    glGenBuffers(1, &EBO);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(unsigned int)*NO_BUTTONS * 6, indices, GL_DYNAMIC_DRAW); 

    unsigned int lightCubeVAO;
    glGenVertexArrays(1, &lightCubeVAO);
    glBindVertexArray(lightCubeVAO);
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);

    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 5 * sizeof(float), (void*)0);
    glVertexAttribPointer(1, 2, GL_FLOAT, GL_FALSE, 5 * sizeof(float), (void*)(3 * sizeof(float)));
    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);

    glGenVertexArrays(1, &modelVAO);
    glGenBuffers(1, &modelVBO);
    glGenBuffers(1, &borderCubeVBO);
    glGenVertexArrays(1, &borderCubeVAO);
    glBindBuffer(GL_ARRAY_BUFFER, borderCubeVBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices_b), vertices_b, GL_STATIC_DRAW);
    glBindVertexArray(borderCubeVAO);
    glBindBuffer(GL_ARRAY_BUFFER, borderCubeVBO);
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);
    glEnableVertexAttribArray(0);

    // render loop
    // -----------
    while (!glfwWindowShouldClose(window))
    {
        if(firstRun) {
            firstRun = false;
            triangle_counter = 0;
            memset(marked,0,(size-1)*(size-1)*(size-1)*sizeof(unsigned int));
            if(use_query_cell) {
                for(unsigned int z=0;z<(size-1);z++) {
                    if(query_found) break;
                    for(unsigned int y=0;y<(size-1);y++) {
                        if(query_found) break;
                        for(unsigned int x=0;x<(size-1);x++) {
                            //cube vertices
                            float *v1 = vertices[x][y][z];
                            float *v2 = vertices[x+1][y][z];
                            float *v3 = vertices[x+1][y+1][z];
                            float *v4 = vertices[x][y+1][z];
                            float *v5 = vertices[x][y][z+1];
                            float *v6 = vertices[x+1][y][z+1];
                            float *v7 = vertices[x+1][y+1][z+1];
                            float *v8 = vertices[x][y+1][z+1];
                            float min = get_min(v1[6],v2[6],v3[6],v4[6],v5[6],v6[6],v7[6],v8[6]);
                            float max = get_max(v1[6],v2[6],v3[6],v4[6],v5[6],v6[6],v7[6],v8[6]);
                            if(iso_value > min && iso_value < max) {
                                query_cell_x = v1[0];
                                query_cell_y = v1[1];
                                query_cell_z = v1[2];
                                query_found = true;
                                break;
                            }
                        }
                    }
                }
                if(query_found) {
                    enqueue(queue, query_cell_x, query_cell_y, query_cell_z);
                }
            }
            //printf("%d - %d - %d",query_cell_x,query_cell_y,query_cell_z);
            print("query cell found");
            start = clock();
            while(q_current > 0) {
                float *v = dequeue(queue);
                int x,y,z;
                x = v[0];
                y = v[1];
                z = v[2];
                float *v1 = vertices[x][y][z];
                float *v2 = vertices[x+1][y][z];
                float *v3 = vertices[x+1][y+1][z];
                float *v4 = vertices[x][y+1][z];
                float *v5 = vertices[x][y][z+1];
                float *v6 = vertices[x+1][y][z+1];
                float *v7 = vertices[x+1][y+1][z+1];
                float *v8 = vertices[x][y+1][z+1];
                float min = get_min(v1[6],v2[6],v3[6],v4[6],v5[6],v6[6],v7[6],v8[6]);
                float max = get_max(v1[6],v2[6],v3[6],v4[6],v5[6],v6[6],v7[6],v8[6]);
                if(iso_value > min && iso_value < max) {
                    if(x+1 < size && !marked[x+1][y][z]) {
                        enqueue(queue,x+1,y,z);
                        marked[x+1][y][z] = 1;
                    }
                    if(x-1 >= 0 && !marked[x-1][y][z]) {
                        enqueue(queue,x-1,y,z);
                        marked[x-1][y][z] = 1;
                    }
                    if(y+1 < size && !marked[x][y+1][z]) {
                        enqueue(queue,x,y+1,z);
                        marked[x][y+1][z] = 1;
                    }
                    if(y-1 >= 0 && !marked[x][y-1][z]) {
                        enqueue(queue,x,y-1,z);
                        marked[x][y-1][z] = 1;
                    }
                    if(z+1 < size && !marked[x][y][z+1]) {
                        enqueue(queue,x,y,z+1);
                        marked[x][y][z+1] = 1;
                    }
                    //printf("z: %d",z);
                    if(z-1 >= 0 && !marked[x][y][z-1]) {
                        enqueue(queue,x,y,z-1);
                        marked[x][y][z-1] = 1;
                    }
                    handle_tetrahedron(v3, v4, v6, v7, triangles);
                    handle_tetrahedron(v4, v5, v6, v8, triangles);
                    handle_tetrahedron(v1, v2, v4, v6, triangles);
                    handle_tetrahedron(v1, v5, v6, v4, triangles);
                    handle_tetrahedron(v4, v6, v7, v8, triangles);
                    handle_tetrahedron(v2, v3, v4, v6, triangles);
                }
            }
            print("conour prop done");
        printf("contour propagation time: %f\n", ((double) (clock() - start)) / CLOCKS_PER_SEC);

            glBindBuffer(GL_ARRAY_BUFFER, modelVBO);
            glBufferData(GL_ARRAY_BUFFER,triangle_counter * 3 * 3* sizeof(float)/**(size-1)*(size-1)*(size-1)*6*2*3*3*/, triangles, GL_STATIC_DRAW);
            glBindVertexArray(modelVAO);
            glBindBuffer(GL_ARRAY_BUFFER, modelVBO);
            glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);
            glEnableVertexAttribArray(0);

            drawingTriangles = triangle_counter;
            setup_ui();
            resend_data();
        }

        //preprocess end
        //
        // per-frame time logic
        // --------------------
        float currentFrame = glfwGetTime();
        deltaTime = currentFrame - lastFrame;
        lastFrame = currentFrame;

        processInput(window);

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        modelShader.use();

        glm::mat4 projection = glm::perspective(glm::radians(camera.Zoom), (float)scr_width / (float)scr_height, 0.1f, 100.0f);
        glm::mat4 view = camera.GetViewMatrix();
        modelShader.setMat4("projection", projection);
        modelShader.setMat4("view", view);

        // world transformation
        glm::mat4 model = glm::mat4(1.0f);
        model = glm::scale(model,glm::vec3(1/20.0f,1/20.0f,1/20.0f));
        modelShader.setMat4("model", model);

        glBindVertexArray(modelVAO);
        glDrawArrays(GL_TRIANGLES, 0, drawingTriangles*3);
        if(drawingTriangles < triangle_counter)
            drawingTriangles += animation_multiplier;
        else drawingTriangles = triangle_counter;

        //border cube
        glBindVertexArray(borderCubeVAO);
        glDrawArrays(GL_LINES, 0, 24);

        uiShader.use();
        glBindVertexArray(lightCubeVAO);
        glDrawElements(GL_TRIANGLES, NO_BUTTONS * 6, GL_UNSIGNED_INT, 0);

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    // optional: de-allocate all resources once they've outlived their purpose:
    // ------------------------------------------------------------------------
    glDeleteVertexArrays(1, &modelVAO);
    glDeleteVertexArrays(1, &borderCubeVAO);
    glDeleteBuffers(1, &modelVBO);
    glDeleteBuffers(1, &borderCubeVBO);
    glDeleteVertexArrays(1, &lightCubeVAO);
    glDeleteBuffers(1, &VBO);

    // glfw: terminate, clearing all previously allocated GLFW resources.
    // ------------------------------------------------------------------
    glfwTerminate();
    free(textureRegions);
    return 0;
}

// process all input: query GLFW whether relevant keys are pressed/released this frame and react accordingly
// ---------------------------------------------------------------------------------------------------------
void processInput(GLFWwindow *window)
{
    if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
        glfwSetWindowShouldClose(window, true);

    if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS)
        camera.ProcessKeyboard(FORWARD, deltaTime);
        ;
    if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS)
        camera.ProcessKeyboard(BACKWARD, deltaTime);
        ;
    if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS)
        camera.ProcessKeyboard(LEFT, deltaTime);
        ;
    if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS)
        camera.ProcessKeyboard(RIGHT, deltaTime);
        ;
}

// glfw: whenever the window size changed (by OS or user resize) this callback function executes
// ---------------------------------------------------------------------------------------------
void framebuffer_size_callback(GLFWwindow* window, int width, int height)
{
    // make sure the viewport matches the new window dimensions; note that width and 
    // height will be significantly larger than specified on retina displays.
    glViewport(0, 0, width, height);
    std::cout << "resize " << width << " : " << height <<std::endl;
    scr_height = height;
    scr_width = width;
}


void mouse_callback(GLFWwindow* window, double xpos, double ypos)
{
    if (firstMouse)
    {
        lastX = xpos;
        lastY = ypos;
        firstMouse = false;
    }

    float xoffset = xpos - lastX;
    float yoffset = lastY - ypos; // reversed since y-coordinates go from bottom to top

    lastX = xpos;
    lastY = ypos;

    if(rightClickPressed) {
        camera.ProcessMouseMovement(xoffset, yoffset);
    }
}
// -------------------------------------------------------
void mouse_button_callback(GLFWwindow* window, int button, int action, int mods)
{
    if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS) {
        double xpos, ypos;
        //getting cursor position
        glfwGetCursorPos(window, &xpos, &ypos);
        normalized_coordinates(xpos, ypos, &xpos, &ypos);
        if(ypos >= 1-MENU_HEIGHT) {
            click_button(xpos);
        }
    }
    else if (button == GLFW_MOUSE_BUTTON_RIGHT && action == GLFW_PRESS) {
        rightClickPressed = true;
    }
    else if (button == GLFW_MOUSE_BUTTON_RIGHT && action == GLFW_RELEASE) {
        rightClickPressed = false;
    }
}
void scroll_callback(GLFWwindow* window, double xoffset, double yoffset)
{
    camera.ProcessMouseScroll(yoffset);
}
//uses malloc, so remmember to free after call.
int load_datafile(char const *offFile, void ** scalarField_vp, unsigned int *max_scalar) {
    FILE *file;
    int buffer[10];
    unsigned int size = 0;
	file = fopen(offFile, "r");
	fscanf(file, "%d %d %d", &size,&buffer[0],&buffer[1]);
    //assuming a cube
    //ignoring origin
	fscanf(file, "%s %u %u %u", (char *)buffer, &buffer[0], &buffer[1], &buffer[2]);
    //ignoring stepsize lets assume it will be 1x1x1
	fscanf(file, "%s %u %u %u", (char *)buffer, &buffer[0], &buffer[1], &buffer[2]);
    unsigned int (*scalarField)[size][size] = (unsigned int (*)[size][size])malloc(sizeof(int)*size*size*size);
    for(int i=0;i<size;i++) {
        for(int j=0;j<size;j++) {
            for(int k=0;k<size;k++) {
                fscanf(file, "%u", &scalarField[i][j][k]);
                if(*max_scalar < scalarField[i][j][k]) {
                    *max_scalar = scalarField[i][j][k];
                }
            }
        }
    }
    *scalarField_vp = (void *)scalarField;
    return size;
}
void handle_tetrahedron(float *v3, float *v4, float *v6, float *v7, float (*triangles)[3][3]) {
    bool v3_isWhite, v4_isWhite, v6_isWhite, v7_isWhite;

    if(v3[6] > iso_value) v3_isWhite = true;
    else v3_isWhite = false;
    if(v4[6] > iso_value) v4_isWhite = true;
    else v4_isWhite = false;
    if(v6[6] > iso_value) v6_isWhite = true;
    else v6_isWhite = false;
    if(v7[6] > iso_value) v7_isWhite = true;
    else v7_isWhite = false;
    //two triangles
    unsigned int w=0,b=0;
    float *whites[4];
    float *blacks[4];
    if(v3_isWhite) {
        whites[w] = v3;
        w++;
    }
    else {//v3 black
        blacks[b] = v3;
        b++;
    }
    if(v4_isWhite) {
        whites[w] = v4;
        w++;
    }
    else {//v4 black
        blacks[b] = v4;
        b++;
    }
    if(v6_isWhite) {
        whites[w] = v6;
        w++;
    }
    else {//v6 black
        blacks[b] = v6;
        b++;
    }
    if(v7_isWhite) {
        whites[w] = v7;
        w++;
    }
    else {//v7 black
        blacks[b] = v7;
        b++;
    }
    if(w == 2 && b == 2) {
        triangles[triangle_counter][0][0] = (whites[0][0] + blacks[0][0])/2.0f;
        triangles[triangle_counter][0][1] = (whites[0][1] + blacks[0][1])/2.0f;
        triangles[triangle_counter][0][2] = (whites[0][2] + blacks[0][2])/2.0f;

        triangles[triangle_counter][1][0] = (whites[0][0] + blacks[1][0])/2.0f;
        triangles[triangle_counter][1][1] = (whites[0][1] + blacks[1][1])/2.0f;
        triangles[triangle_counter][1][2] = (whites[0][2] + blacks[1][2])/2.0f;

        triangles[triangle_counter][2][0] = (whites[1][0] + blacks[0][0])/2.0f;
        triangles[triangle_counter][2][1] = (whites[1][1] + blacks[0][1])/2.0f;
        triangles[triangle_counter][2][2] = (whites[1][2] + blacks[0][2])/2.0f;

        triangle_counter++;

        triangles[triangle_counter][0][0] = (whites[0][0] + blacks[1][0])/2.0f;
        triangles[triangle_counter][0][1] = (whites[0][1] + blacks[1][1])/2.0f;
        triangles[triangle_counter][0][2] = (whites[0][2] + blacks[1][2])/2.0f;

        triangles[triangle_counter][1][0] = (whites[1][0] + blacks[1][0])/2.0f;
        triangles[triangle_counter][1][1] = (whites[1][1] + blacks[1][1])/2.0f;
        triangles[triangle_counter][1][2] = (whites[1][2] + blacks[1][2])/2.0f;

        triangles[triangle_counter][2][0] = (whites[1][0] + blacks[0][0])/2.0f;
        triangles[triangle_counter][2][1] = (whites[1][1] + blacks[0][1])/2.0f;
        triangles[triangle_counter][2][2] = (whites[1][2] + blacks[0][2])/2.0f;
        triangle_counter++;
    }
    else if(w == 3 && b == 1) {
        triangles[triangle_counter][0][0] = (whites[0][0] + blacks[0][0])/2.0f;
        triangles[triangle_counter][0][1] = (whites[0][1] + blacks[0][1])/2.0f;
        triangles[triangle_counter][0][2] = (whites[0][2] + blacks[0][2])/2.0f;

        triangles[triangle_counter][1][0] = (whites[1][0] + blacks[0][0])/2.0f;
        triangles[triangle_counter][1][1] = (whites[1][1] + blacks[0][1])/2.0f;
        triangles[triangle_counter][1][2] = (whites[1][2] + blacks[0][2])/2.0f;

        triangles[triangle_counter][2][0] = (whites[2][0] + blacks[0][0])/2.0f;
        triangles[triangle_counter][2][1] = (whites[2][1] + blacks[0][1])/2.0f;
        triangles[triangle_counter][2][2] = (whites[2][2] + blacks[0][2])/2.0f;
        triangle_counter++;
    }
    else if(w == 1 && b == 3) {
        triangles[triangle_counter][0][0] = (whites[0][0] + blacks[0][0])/2.0f;
        triangles[triangle_counter][0][1] = (whites[0][1] + blacks[0][1])/2.0f;
        triangles[triangle_counter][0][2] = (whites[0][2] + blacks[0][2])/2.0f;

        triangles[triangle_counter][1][0] = (whites[0][0] + blacks[1][0])/2.0f;
        triangles[triangle_counter][1][1] = (whites[0][1] + blacks[1][1])/2.0f;
        triangles[triangle_counter][1][2] = (whites[0][2] + blacks[1][2])/2.0f;

        triangles[triangle_counter][2][0] = (whites[0][0] + blacks[2][0])/2.0f;
        triangles[triangle_counter][2][1] = (whites[0][1] + blacks[2][1])/2.0f;
        triangles[triangle_counter][2][2] = (whites[0][2] + blacks[2][2])/2.0f;
        triangle_counter++;
    }
}

float get_min(float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
    float min = f1;
    if(f2 < min) min = f2;
    if(f3 < min) min = f3;
    if(f4 < min) min = f4;
    if(f5 < min) min = f5;
    if(f6 < min) min = f6;
    if(f7 < min) min = f7;
    if(f8 < min) min = f8;
    return min;
}
float get_max(float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
    float max = f1;
    if(f2 > max) max = f2;
    if(f3 > max) max = f3;
    if(f4 > max) max = f4;
    if(f5 > max) max = f5;
    if(f6 > max) max = f6;
    if(f7 > max) max = f7;
    if(f8 > max) max = f8;
    return max;
}
void enqueue(float queue[][3], float x, float y, float z) {
    if(q_end >= q_capacity) {
        q_end = 0;
    }
    queue[q_end][0] = x;
    queue[q_end][1] = y;
    queue[q_end][2] = z;
    q_end++;
    q_current++;
}
float * dequeue(float queue[][3]) {
    //printf("dq start %d\n",q_begin);
    float *retval = queue[q_begin];
    q_begin++;
    if(q_begin >= q_capacity) {
        q_begin = 0;
    }
    q_current--;
    print("dq end ");
    return retval;
}

void normalized_coordinates(int x, int y, double *normX, double *normY) {
    y = scr_height - y;
    float tx = (float)x/scr_width;
    float ty = (float)y/scr_height;
    //it's in range 0-1 now
    tx *= 2; ty *= 2;
    //it's in range 0-2 now
    tx -= 1; ty -= 1;
    //it's in range -1 to 1 now
    *normX = tx;
    *normY = ty;
}
TextureRegion * load_texture_region() {

    FILE *fp;
    fp = fopen("./icons/project.atlas","r");
    if(!fp) {
        printf("error opening file\n");
        exit(1);
    }
    //get rid of header
    fgets(buffer, BUFFER_SIZE, fp);
    fgets(buffer, BUFFER_SIZE, fp);
    fscanf(fp, "size: %d,%d\n", &TextureRegionXSize,&TextureRegionYSize);
    fgets(buffer, BUFFER_SIZE, fp);
    fgets(buffer, BUFFER_SIZE, fp);
    fgets(buffer, BUFFER_SIZE, fp);

    struct TextureRegion *textureRegions = (TextureRegion *)malloc(sizeof(TextureRegion)*NO_TEXTURE_REGION);
    for(int i=0; i<NO_TEXTURE_REGION ;i++) {
        TextureRegion &region = textureRegions[i];
        fscanf(fp, "%s\n",region.name);
        /* fgets(buffer, BUFFER_SIZE, fp); */
        /* strcpy(region.name, buffer); */
        fgets(buffer, BUFFER_SIZE, fp); //ignore rotate
        fscanf(fp, "  xy: %d, %d\n",&region.x, &region.y);
        fscanf(fp, "  size: %d, %d\n",&region.width, &region.height);
        fgets(buffer, BUFFER_SIZE, fp); //ignore orig
        fgets(buffer, BUFFER_SIZE, fp); //ignore offset
        fgets(buffer, BUFFER_SIZE, fp); //ignore index
        //printf("%s",region.name);
        //fgets(buffer, BUFFER_SIZE, fp);
    }
    fclose(fp);
    return textureRegions;
}
TextureRegion & get_texture_region(const char * name) {
    for(int i=0; i<NO_TEXTURE_REGION; i++) {
        if(strcmp(textureRegions[i].name, name) == 0) {//if equal
            return textureRegions[i];//reference is being returned.
        }
    }
    printf("Texture region not found %s",name);
    exit(1);
}
void set_right_of(float right[4][5], float left[4][5]) {
    right[0][0] = left [0][0] + BUTTON_SIZE;
    right[0][1] = left [0][1];
    right[0][2] = left [0][2];

    right[1][0] = left [1][0] + BUTTON_SIZE;
    right[1][1] = left [1][1];
    right[1][2] = left [1][2];

    right[2][0] = left [2][0] + BUTTON_SIZE;
    right[2][1] = left [2][1];
    right[2][2] = left [2][2];

    right[3][0] = left [3][0] + BUTTON_SIZE;
    right[3][1] = left [3][1];
    right[3][2] = left [3][2];
}
float draw_text_at(float buttons[][4][5],char *text,float x, float y, float height/*=0.125*/) {
    float xadvance = 0;
    for(int i=0; text[i] != '\0'; i++) {
        place_at(buttons[i], x+xadvance, y, height, height);
        xadvance += height*0.45;
        create_char(text[i],buttons[i]);
    }
    return xadvance;
}
void create_char(char c,float button[4][5]) {
    c = c-START_CHAR;
    int rowNum = c/ROW_LENGTH;
    int columnNum = c % ROW_LENGTH;
    TextureRegion &fontregion = *fontRegionP;
    button[0][3] = (fontregion.x + columnNum*WIDTH_OF_CHAR)/(float)TextureRegionXSize;

    button[2][4] = (fontregion.y + rowNum*HEIGHT_OF_CHAR)/(float)TextureRegionYSize;

    button[0][4] = button[2][4] + HEIGHT_OF_CHAR/(float)TextureRegionYSize;

    button[1][3] = button[0][3] + WIDTH_OF_CHAR/(float)TextureRegionXSize;
    button[1][4] = button[0][4];
                   
    button[2][3] = button[1][3];
                   
    button[3][3] = button[0][3];
    button[3][4] = button[2][4];
    /* float buttonHeight = button[3][1] - button[0][1]; */
    /* float buttonWidth = buttonHeight * (WIDTH_OF_CHAR/HEIGHT_OF_CHAR); */
    /* place_at(button, button[0][0], button[0][1], buttonWidth, buttonHeight); */
}
void place_at(float button[4][5], float x, float y, float width, float height) {
    button[0][0] = x;
    button[0][1] = y;

    button[1][0] = x+width;
    button[1][1] = y;

    button[2][0] = x+width;
    button[2][1] = y+height;

    button[3][0] = x;
    button[3][1] = y+height;
}
void map_texture_to_button(const char * name, float button[4][5]) {
    TextureRegion &region = get_texture_region(name);
    button[0][3] = region.x/(float)TextureRegionXSize;
    button[0][4] = (region.y+region.height)/(float)TextureRegionYSize;

    button[1][3] = (region.x+region.width)/(float)TextureRegionXSize;
    button[1][4] = (region.y+region.height)/(float)TextureRegionYSize;
                    
    button[2][3] = (region.x+region.width)/(float)TextureRegionXSize;
    button[2][4] = region.y/(float)TextureRegionYSize;
                    
    button[3][3] = region.x/(float)TextureRegionXSize; 
    button[3][4] = region.y/(float)TextureRegionYSize; 
}
void printButton(float button[4][5]){
    float *b = button[0];
    printf("x:%f, y:%f, z:%f, s:%f, t:%f\n",b[0],b[1],b[2],b[3],b[4]);
    b = button[1];
    printf("x:%f, y:%f, z:%f, s:%f, t:%f\n",b[0],b[1],b[2],b[3],b[4]);
    b = button[2];
    printf("x:%f, y:%f, z:%f, s:%f, t:%f\n",b[0],b[1],b[2],b[3],b[4]);
    b = button[3];
    printf("x:%f, y:%f, z:%f, s:%f, t:%f\n",b[0],b[1],b[2],b[3],b[4]);
}
void click_button(float xpos) {
    if(b_iso_left[0][0] < xpos && b_iso_left[1][0] > xpos) {
        iso_value -= 10;
        use_query_cell = true;
        query_found = false;
        firstRun = true;
    }
    else if(b_iso_right[0][0] < xpos && b_iso_right[1][0] > xpos) {
        iso_value += 10;
        use_query_cell = true;
        query_found = false;
        firstRun = true;
    }
    else if(b_query[0][0] < xpos && b_query[1][0] > xpos) {
        reading_query_cell = true;
        sprintf(buffer+100,"%11s","|\0");
        draw_text_at(b_query_text,buffer+100,b_query_text[0][0][0]);
        resend_data();
    }
    else if(b_animation_left[0][0] < xpos && b_animation_left[1][0] > xpos) {
        animation_multiplier -= 10;
        if(animation_multiplier < 1) animation_multiplier = 1;
        setup_ui();
        resend_data();
    }
    else if(b_animation_right[0][0] < xpos && b_animation_right[1][0] > xpos) {
        animation_multiplier += 10;
        setup_ui();
        resend_data();
    }
    else if(b_animation_play[0][0] < xpos && b_animation_play[1][0] > xpos) {
        drawingTriangles = 1;

    }
    else if(b_iso_text[0][0][0] < xpos && b_iso_text[6][1][0] > xpos) {
        reading_iso_value = true;
        sprintf(buffer+100,"%7s","|\0");
        draw_text_at(b_iso_text,buffer+100,b_iso_text[0][0][0]);
        resend_data();
    }
    else if(b_query_text[0][0][0] < xpos && b_query_text[10][1][0] > xpos) {
        reading_query_cell = true;
        sprintf(buffer+100,"%11s","|\0");
        draw_text_at(b_query_text,buffer+100,b_query_text[0][0][0]);
        resend_data();
    }
    else if(b_off_text[0][0][0] < xpos && b_off_text[10][1][0] > xpos) {
        save_off_file();
    }
    buffer_counter = 0;
    buffer[0] = '\0';
}
void setup_ui() {
    place_at(buttons[0],-1,1-(MENU_HEIGHT),2,BUTTON_SIZE);
    place_at(buttons[1],-1,1-(MENU_HEIGHT),BUTTON_SIZE,BUTTON_SIZE);

    
    map_texture_to_button("blue", buttons[0]);
    map_texture_to_button("left", buttons[1]);
    map_texture_to_button("right", buttons[2]);
    b_iso_left = buttons[1];
    b_iso_right = buttons[2];
    set_right_of(buttons[2],buttons[1]);
    set_right_of(buttons[3],buttons[2]);
    sprintf(buffer,"%7.2f",iso_value);
    float adv = draw_text_at(&buttons[3],buffer,buttons[3][0][0],1-MENU_HEIGHT,MENU_HEIGHT);
    b_iso_text = &buttons[3];

    place_at(buttons[10],buttons[3][0][0]+adv+BUTTON_SIZE,1-(MENU_HEIGHT),BUTTON_SIZE,BUTTON_SIZE);
    map_texture_to_button("query",buttons[10]);
    b_query = buttons[10];

    set_right_of(buttons[11],buttons[10]);
    sprintf(buffer,"%3d,%3d,%3d",query_cell_x,query_cell_y,query_cell_z);
    adv = draw_text_at(&buttons[11],buffer,buttons[11][0][0]);
    b_query_text = &buttons[11];

    place_at(buttons[22],buttons[11][0][0]+adv+BUTTON_SIZE,1-(MENU_HEIGHT),BUTTON_SIZE,BUTTON_SIZE);
    sprintf(buffer,"%12s","Off");
    adv = draw_text_at(&buttons[22],buffer,buttons[22][0][0]);
    b_off_text = &buttons[22];

    map_texture_to_button("left", buttons[34]);
    b_animation_left = buttons[34];
    map_texture_to_button("right", buttons[35]);
    b_animation_right = buttons[35];
    place_at(buttons[34],buttons[23][0][0]+adv+BUTTON_SIZE,1-(MENU_HEIGHT),BUTTON_SIZE,BUTTON_SIZE);
    set_right_of(buttons[35],buttons[34]);

    set_right_of(buttons[36],buttons[35]);
    sprintf(buffer,"%2d",animation_multiplier);
    adv = draw_text_at(&buttons[36],buffer,buttons[36][0][0]);
    place_at(buttons[38],buttons[36][0][0]+adv+BUTTON_SIZE,1-(MENU_HEIGHT),BUTTON_SIZE,BUTTON_SIZE);
    map_texture_to_button("right_round", buttons[38]);
    b_animation_play = buttons[38];
}
void key_callback(GLFWwindow* window, int key, int scancode, int action, int mods)
{
    if((key == 335 || key == 257) && action == 1) {
        buffer[buffer_counter] = '\0';
        if(reading_query_cell) {
            reading_query_cell = false;
            sscanf(buffer,"%d,%d,%d",&query_cell_x,&query_cell_y,&query_cell_z);
            q_begin = 0;
            q_end = 0;
            q_current = 0;
            enqueue(queue, query_cell_x, query_cell_y, query_cell_z);
            firstRun = true;
        }
        else if(reading_iso_value) {
            reading_iso_value = false;
            sscanf(buffer,"%f",&iso_value);
            q_begin = 0;
            q_end = 0;
            q_current = 0;
            use_query_cell = true;
            query_found = false;
            firstRun = true;

        }
        setup_ui();
        resend_data();
    }
}
void resend_data() {
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    glBufferSubData(GL_ARRAY_BUFFER, 0, sizeof(float)*NO_BUTTONS*4*5, buttons);
}
void character_callback(GLFWwindow* window, unsigned int codepoint)
{
    buffer[buffer_counter++] = codepoint;
    buffer[buffer_counter] = '|';
    buffer[buffer_counter+1] = '\0';
    if(reading_iso_value) {
        sprintf(buffer+100,"%7s",buffer);
        draw_text_at(b_iso_text,buffer+100,b_iso_text[0][0][0]);
        resend_data();
    }
    else if(reading_query_cell) {
        sprintf(buffer+100,"%11s",buffer);
        draw_text_at(b_query_text,buffer+100,b_query_text[0][0][0]);
        resend_data();
    }
}
void print(const char *str) {
    if(false)
    std::cout<<str<<std::endl;
}
void load_seed_file(char * file) {
    FILE *fp = fopen(file,"r");
    fscanf(fp,"%d ",&no_seeds);
    seeds = (unsigned int (*)[3])malloc(sizeof(unsigned int)*3*no_seeds);
    for(int i=0;i<no_seeds;i++) {
        fscanf(fp,"%d,%d,%d ",&seeds[i][0],&seeds[i][1],&seeds[i][2]);
    }
    fclose(fp);
}
void save_off_file() {
    FILE *fp = fopen("model.off","w");
    fprintf(fp,"OFF\n");
    fprintf(fp,"%d %d 0\n",triangle_counter*3, triangle_counter);
    for(int i=0;i<triangle_counter; i++) {
        fprintf(fp,"%f %f %f\n",triangles[i][0][0],triangles[i][0][1],triangles[i][0][2]);
        fprintf(fp,"%f %f %f\n",triangles[i][1][0],triangles[i][1][1],triangles[i][1][2]);
        fprintf(fp,"%f %f %f\n",triangles[i][2][0],triangles[i][2][1],triangles[i][2][2]);
    }
    for(int i=0;i<triangle_counter; i++) {
        fprintf(fp,"3 %d %d %d\n",3*i,3*i+1,3*i+2);
    }
    fclose(fp);
}
