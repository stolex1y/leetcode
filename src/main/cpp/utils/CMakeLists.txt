cmake_minimum_required(VERSION 3.22.0)

project(utils CXX)
set(CMAKE_CXX_STANDARD 20)

add_library(
        libutils
        INTERFACE
        include/tree_node.h
        include/test_utils.h
        include/graph.h
)
target_include_directories(libutils INTERFACE include)
