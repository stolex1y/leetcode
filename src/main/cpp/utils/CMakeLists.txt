cmake_minimum_required(VERSION 3.22.0)

project(utils CXX)
set(CMAKE_CXX_STANDARD 20)

cmake_policy(SET CMP0167 NEW)
find_package(Boost 1.85.0 REQUIRED)

add_library(
        libutils
        INTERFACE
        include/tree_node.h
        include/test_utils.h
        include/graph.h
        include/list_node.h
        include/container_utils.h
        src/graph.cpp
)
target_include_directories(libutils INTERFACE include)
target_link_libraries(libutils INTERFACE Boost::boost)
