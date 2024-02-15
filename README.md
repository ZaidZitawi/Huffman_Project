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


## Quick Sight:



<p align="center">
  <img src="https://github.com/ZaidZitawi/Huffman_Project/assets/111902956/2f73a10c-38b3-49df-bed7-7b2b34d0d15e" alt="Reading Data Option" width="800" style="border: 2px solid black; box-shadow: 2px 2px 5px grey;">
</p>

