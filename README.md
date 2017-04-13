# CS276 inverted index compression exercises

Think about the Spotlight index on your Mac (or the equivalent on
Windows or Ubuntu):
You want it small, and mainly on-disk, but fast. Small means not only
that it uses less of your hard disk, but it is quicker to load
a small postings list from disk and this gives you speed.
On my Mac, it takes about 12GB to index the ~500GB of content on my Mac,
in a positional index. (Note: Okay, I do have some music and photos on
my Mac, but I am an NLP researcher: I have over 100 GB of text
corpora, tweet collections, etc. and lots of papers and documents, all indexed.)

Google has a lot of disk space (!), but Google still does index compression! **Why?**

For many years, Jeff Dean used to talk in CS276 about the large-scale
systems issues encountered during the
early years of Google. You can see roughly the same content in 
[his WSDM 2009 talk](https://static.googleusercontent.com/media/research.google.com/en//people/jeff/WSDM09-keynote.pdf).
When Google was starting
out they really worked hard on compressing their index. They couldn't
afford not to! For reasons of available money, query servicing speed,
and the lead time in building out new server racks as the company grew, they could only 
keep up with their huge rate of growth by getting better and better at 
compressing their index. Their goal at one point was to improve their
index compression by 15% a month!

Today, things at Google are a bit different (they have a lot of money…), but the web is
alos much bigger. The enormous
savings in the number of machines needed for index serving resulting
from compression still totally justify
compressing the index. Moreover, it lets Google answer your queries more
quickly, *even when the index is in memory or on SSDs*. With modern computers, the
CPU and cache are so much faster than main memory (which is so much faster
than disk) that you get more speed by using a compressed index, even though
you have to do work decompressing it each time you search. And you can serve
the index from less machines which reduces network latencies.
Touching less of each of memory/disk/machines is a big deal!

There are two basic families of compression methods:

1. "Traditional" methods focused on bit-level representation and coding:
   Huffman, Golumb, Gamma, Delta, Rice, ….
2. "Byte aligned" or "word aligned" encodings, where roughly there are stretches
   of bits that you can use directly as numbers, and you mainly access them by
   masking and shifting.

The latter have wiped out the former. They are a little less compact, but they
are way faster to decode. (And, besides, the latter make your brain hurt less.)
 
In index compression, there are two concerns:

1. size
2. speed

To some extent, improving size improves speed (less stuff to stream
from disk/memory). 
But you can overdo it: A simple byte aligned code gains more in
decoding speed than it loses in non-optimal compression.
Today in class, we'll only look at size not speed though.

Clone this repository for today's exercise.
To get the code here in Eclipse, do `File|Import` choose `Git|Projects from Git`, press `Next`,
`Clone URI` then `Next`, then enter the HTTPS URI on this page, and `Next`, `Next`, `Next`, 
go with `Import existing projects`, `Next` and `Finish` – and you should be all ready to go!
(The code uses the Java 7 diamond operator – if you last used Java in CS106A, then you do
need to update to a more modern version of Eclipse. Make sure your Java language level is
set to Java 7 or Java 8.)


**1.**
Look at the example code (IndexCompression.java). Towards the bottom of the file,
it contains selected postings lists taken from the PA1 data.
Suppose we use lists of integers for a postings list.

* How big will the postings list for *linguistics* be?
* How big will the postings list for *the* be?

Suppose we use a bitmap for whether a word is in a document (that is, bit *i*
is 1 iff the document contains word *i* in the Dictionary).

* How big will the postings list for *the* be?
* How big will the postings list for *linguistics* be?

How many times more or less efficient is one encoding for a word of a certain
frequency? What we want is an encoding method that can make postings lists 
of terms of different frequencies all small.

### Code walkthrough:

- The `main` method at the end runs unit tests and benchmark tests
  which measure size and correctness for the postings lists from PA1. 
- Feel free to add more `if`/`then`s if you want to try other encodings
- For any compression scheme, in the middle you should have a count of
  bytes, such as by putting everything in a `ByteArrayOutputStream` or a `byte[]`.
  These are real bytes that we can count for postings list size!
- What's currently there is just a null encoder that takes docIDs as int's and 
  writes bytes.
 
 ### Some reminders/teachings on bytes and bitwise operations

You may not have ever seen or used much operations in Java for byte or bit level
processing. So here are a few pointers.

Java supports hexadecimal and binary constants: `0xFC` or `0b1100`.

In Java, `int`, `short`, and `byte` are primitive types that take 4,
2, and 1 byte guaranteed. You can convert an `int` into a `byte`
simply by casting:
```
int a = 32;
byte b = (byte) a;
```
A `byte` is just a byte, but Java (somewhat unfortunately) doesn't
have unsigned types and by default will treat a `byte` as a signed
twos-complement number if you convert it into something bigger or
print it out. There are some static methods in the `Byte` class that
will treat a `byte` as unsigned, e.g. `Byte.toUnsignedInt(byte)`.

Java has bit manipulation operators basically like C, which operate on
the primitive types. That is, you have &, |, ^, and ~ for bitwise and,
or, xor, and binary ones complement. There are also bit shift operators: <<, >>,
and >>> for left shift, right shift, and a zero-filling right shift
respectively. The & operator is especially
useful for masking (selecting out part of a byte or int and the |
operator is especially useful for combining two numbers that each
store some of the bits. The bit shift operators are used with them to
place bytes in the right place in a number.  Something like:
`b = x & 0xF | (y & 0xF << 4)`.

There are also a few other classes that are highly useful:

* The `BitSet` class efficiently stores an arbitrary-size array of
bits. It's very convenient for working with bits and you can then
output what you build as a native Java array.
* A `byte[]` is the basic structure for having an array of bytes that
  you work with by yourself.
* A `ByteArrayOutputStream` will allow you to write data using I/O
  methods that is written to a growable byte array. You can get that
  array at the end of writing with the `toByteArray()` method.
* The reverse is a `ByteArrayInputStream`, which you initialize with a
`byte[]` and then you can read bytes from it.


### Tasks to do

There are two initial things to do, and then some more if you have time.

**2.**
Write the two routines (near the top of the file) to change a postings list of 
docIDs to a postings list
of gaps and back: `gapEncode` and `gapDecode`. (See *IIR* Section 5.3.)

**3.**
Variable byte encoding. This is part of the assignment PA1, 
but we can get started in class! Write the two routines (near the top of the file)
to encode an `int` as a variable-byte code and to decode it:
`VBEncodeInteger` and `VBDecodeInteger`.  (See *IIR* Section 5.3.1.)

**4.**
Does Variable byte encoding work well for encoding the postings list for
*the*?  Why or why not? Is just looking at the totals in this example
representative or not of what you'd expect with a bigger dictionary of terms?
Why or why not?

**5.**
It'd then be great for you to try to complete another compression scheme. Here's
some things that you could try. It'd be good to have people do some different
things.

  1. Unary code seems really crazy. No one suggests using just a unary code.
   But it might work okay here.  Try it. (*IIR* Section 5.3.2.) Fill in the
   routines `unaryEncodeInteger` and `unaryDecodeInteger`.  You will almost
   certainly want to know about the Java `BitSet` class for this.
   
  1. With more work, you can implement gamma codes. It requires more bit fiddling
   with `BitSet`. Fill in the routines `gammaEncodeInteger` and
   `gammaDecodeInteger`.
   
  1. If you know some other compression scheme from somewhere else like Huffman or
   Golumb codes, you could try it. (See Wikipedia or the links on the CS276
   webpage.)

  1. Modern work has tended to move beyond byte-aligned encodings to 
   word-aligned encodings, where you can encode several gaps in one word,
   for greater compression. Try to implement something like Simple 9
   compression, explained below. (This is a bit more complex, since you have to encode several
   gaps at once, not just one.) Or look closely at the encodings introduced in
   Jeff Dean's slides (referenced above) and try to implement one of them.

### Simple-9
  
“Simple-9” (Anh and Moffat 2005) is a 32-bit word-aligned encoding scheme, which packs as many gaps 
as possible into a 32-bit word by allocating an equal number of bits to each, 
and encoding a gap g as (g – 1) in binary. The first 4 bits of a 32-bit word 
are used as a selector to indicate how many gaps are being encoded in a word, 
as per the table below, and the other 28 bits are used to represent the gap 
values. Assume that the collection being indexed is less than 200 million 
documents, and so a gap can always be encoded in a single word.

For instance, the postings list: 16384 16400

would be encoded in one 32-bit word as: 0111 01111111111111 00000000001111

since the first two gaps are each less than 2^14 which can be decoded together 
in one word. We might represent more compactly by using [ ] to delineate words, 
and a comma to delineate individual numbers, written in decimal but to be 
encoded in binary, as: [7,16383,15]

**Table: Simple-9 encoding options**

| Selector (4 bits) | Number of coded gaps stored in a word | Length of each code in bits | Left over wasted bits |
| --- | --- | --- | --- |
| 0000 | 28 | 1 | 0 |
| 0001 | 14 | 2 | 0 |
| 0010 | 9 | 3 | 1 |
| 0011 | 7 | 4 | 0 |
| 0100 | 5 | 5 | 3 |
| 0101 | 4 | 7 | 0 |
| 0110 | 3 | 9 | 1 |
| 0111 | 2 | 14 | 0 |
| 1000 | 1 | 28 | 0 |
