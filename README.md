#OS Page Replacement Algorithms in Java

This project demonstrates three of the common page replacement algorithms used by operating systems to perform virtual memory paging. This project is written entirely in Java using JetBrains IntelliJ 2016.1 Ultimate using the Java JDK 1.8.

##FIFO Replacement Algorithm
This algorithm uses the first in, first out (FIFO) concept to handle replacement of pages in the page frame. Essentially, when a fault is encountered and a new page is to be entered, the existing page that is replaced would be the once that was first entered.

##LRU Replacement Algorithm
This algorithm uses the last recently used (LRU) concept to handle replacement of pages in the page frame. This means that page to be replaced upon a fault is the one that, as its name describes, was last used. Therefore, each page in the frame has an age that is incremented on each fault and reset on each hit.

##OPT Replacement Algorithm
This algorithm uses the optimal replacement strategy. This means that the page to be replaced upon a fault is the one that is farthest from next being inserted.

##Design Decisions
This project, due to its use of multiple replacement algorithms which all serve the same purpose, lent itself well to the use of an abstract class. Therefore, I have an abstract class `Replacement` that is then extended by each of the disparate replacement algorithms. This has the benefit of allowing me to write driver code that is decoupled from the actual implementations of each of the algorithms.

## License
The MIT License (MIT)

Copyright (c) 2016 Connor Grady

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.