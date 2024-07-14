## Introduction
The project contains the Fix parser api and 2 demo implementations

Parser API:
- important classes:
  - FixParser: the fix parser
  - FixMessageHandler (interface): handling parsing logic
  - Record (interface) : FixParser output
  - ByteBufferRecord: record with content stored in byte buffer
  - Field: contains field data type and data length
  - Schema: provide Fields for ByteBufferRecord  
  - FieldMapper (interface): mapping fix tag to Field

Implementation - Order:
- this demo can handle New Order message (35=D) and Cancel Order message (35=F) 
- parsed values are stored in byte buffer
- this is a GC free implementation, all memory are allocated in the initial phrase
- for demo purposes, the result only support 2 data type: integer and byte array

Implementation - Simple:
- all parsed values are stored as Integer-String pairs
- there is no performance tuning in this implementation
- here contains a repeating group handling  

## limitation / assumption
- Excepting tag 35 (Message type) is present in the fix message
- The parser only handle fix tags after tag 35 (Tag 8 (BeginString), 9 (BodyLength), 35 (MsgType) are the first 3 tags of a fix message)
- there only a demo but not a generic implementation on repeating group handling - see RepeatingGroupHandlingTest 
- Some constant value like object pool size or buffer size are not configurable 

## Unit Test
Test coverage around 80%

## Performance (for OrderParser) 
handle around 1,000,000 message per second
No GC triggered after initiation
0 memory allocation after initiation
