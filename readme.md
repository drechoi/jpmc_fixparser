## Introduction
The project contains the Fix parser api and 2 demo implementations

Parser API:
- contain base structure of fix parser
- 

Implementation - Order:
- this demo can handle New Order message (35=D) and Cancel Order message (35=F) 
- parsed values are stored in byte buffer
- all memory are allocated in the initial phrase
- for demo purposes, the result only support 2 data type: integer and byte array

Implementation - Simple:
- all parsed values are stored as Integer-String pairs
- there is no performance tuning in this implementation
- here contains a repeating group handling  

## limitation / assumption
- Excepting tag 35 (Message type) is present in the fix message
- The parser only handle fix tags after tag 35 (Tag 8 (BeginString), 9 (BodyLength), 35 (MsgType) are the first 3 tags of a fix message)
- there only a demo but not a generic implementation on repeating group handling
- Some constant value like object pool size or buffer size are not configurable 

## Unit Test
Test coverage > 85%

## Performance 
XXX

## CheckList
- git setup

- Coding
  - scope
    - basic scenario
    - repeating group handling
  
- memory usage optimization 
- performance check

- source code clean up
- 