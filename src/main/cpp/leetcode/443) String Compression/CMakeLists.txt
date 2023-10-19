set(TARGET_NAME "task-443")

add_executable(${TARGET_NAME} main.cpp)

target_link_libraries(${TARGET_NAME} libutils)