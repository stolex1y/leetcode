set(TARGET_NAME "task-200")

add_executable(${TARGET_NAME} main.cpp)

target_link_libraries(${TARGET_NAME} libutils)