# Huffman Coding Compression/Uncompression Algorithm

Huffman coding is a lossless data compression algorithm that assigns variable-length codes to input characters based on their frequencies, with the most frequent characters getting the smallest codes.

## Overview

This project implements Huffman coding for file compression and decompression. It utilizes a priority queue and a custom binary tree design to achieve efficient compression and decompression of files.

## Features

- Reads a specified file and counts the frequency of all bytes in the file.
- Constructs the Huffman coding tree based on the byte frequencies.
- Generates a table of encodings for each byte from the Huffman coding tree.
- Encodes the file and outputs the compressed file.
- Displays the header containing the Huffman code mappings.
- Reads the compressed file, decodes it, and outputs the decompressed file.
- Ensures that the decompressed file matches the original file.