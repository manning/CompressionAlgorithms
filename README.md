# CS276 inverted index compression exercises

Think about the Spotlight index on your Mac (or the equivalent):
You want it small, and mainly on-disk, but fast. Small means not only
that it uses less of your hard disk, but it is quicker to load
a small postings list from disk and this gives you speed.
On my Mac, it takes about 10GB to index the ~480GB of content on my Mac,
in a positional index. (Note: Okay, I do have some music and similar stuff on
my Mac, but I also have a ton of text corpora, tweet collections, etc.,
all indexed.)

Google has a lot of disk space, but Google still does index compression! Why?
You'll probably hear Jeff Dean talk about this on May 19. When Google started
off they really worked hard on compressing their index, because they couldn't
afford not to! For reasons of money, time, and lead time, they could only 
keep up with their huge rate of growth by getting better and better at 
compressing their index. Today, things are a bit different, but the web is
very big. The resulting enormous
savings in the number of machines needed for index serving totally justify
compressing the index. Moreover, it lets Google answer your queries more
quickly. Even when the index is in memory. With modern computers, the
CPU and cache are so much faster than main memory (which is so much faster
than disk) that you get more speed by using a compressed index, even though
you have to do work decompressing it each time you search. And you can serve
the index from less machines which reduces network latencies.
Touching less memory/disk/machines is a big deal!

There are two basic families of compression methods:
1. "Traditional" methods focused on bit-level representation and coding:
   Huffman, Golumb, Gamma, Delta, Rice, ....
2. "Byte aligned" or "word aligned" encodings, where roughly there are stretches
   of bits that you can use directly as numbers, and you mainly access them by
   masking and shifting.
The latter have wiped out the former. They are a little less compact, but they
are way faster to decode. (And, besides, they make your brain hurt less.)
 
In index compression, there are two concerns:
1. size
2. speed
To some extent, improving size improves speed (less stuff to stream
from disk/memory). 
But you can overdo it: A simple byte aligned code gains more in
decoding speed than it loses in non-optimal compression.
Byte aligned encodings have clearly won for what is currently used.

Today in class, we'll only look at size not speed though.

Look at the example code (IndexCompression.java). Towards the bottom of the file,
it contains selected postings lists taken from the PA1 data.
What's the challenge? If we use lists of integers for a postings list,
- the postings list for 'linguistics' is about 12 bytes (2 entries plus
  either recording length or having a terminator)
- the postings list for 'the' is about 4000 bytes
If we used a bitmap for whether a word is in a document:
- the postings list for 'the' would be about 128 bytes (an order of
 magnitude smaller!)
- but the postings list for 'linguistics' would also be 128 bytes (an
 order of magnitude bigger!
What we want is an encoding method that can make different kinds of
postings lists both small.

Code walkthrough:
- The `main` method at the end runs unit tests and benchmark tests
  which measure size and correctness for the postings lists from PA1. 
- Feel free to add more if/then's for other encodings
- For any compression/decompression pair, in the middle you should have a count of
  bytes, such as by putting everything in a `ByteArrayOuputStream` or a `byte[]`.
  These are real bytes that we can count at the bit level for size!
- What's currently there is just a null encoder that takes docIDs as int's and 
  writes bytes and reconstructs the ints's.
 
There are two initial things to do:
 - Change postings list of docIDs to postings list of gaps
 - VB encoding
This is part of the assignment PA1, but we can get started in class!

1. Should really do gaps, because we need it for everything.  But, hey, all our docIDs are 1000 or less!  So VB will shrink things even before we do gaps!  Do that. 

2. You need to make most integers small to achieve good compression: gap encoding

It'd be good to have people do some different things:
- Unary code seems really crazy. No one even teaches unary.  But it might work quite well here.  You could try it.
- Gamma codes. More bit fiddling

Stuff from Jeff Dean's talk.  Simple-9.  See links on cs276 web page.
Or try things like delta, huffman, golumb.  (See Wikipedia
pages, or textbook for gamma and delta codes.)

Questions:
- For this example, in aggregate VB encoding isn't very competitive.
  Is just looking at the totals in this example representative or not of what
  you'd expect with a bigger dictionary of terms?