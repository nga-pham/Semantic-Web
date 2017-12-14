# Review of storage modifiers and linkage

## Storage Duration

- Automatic: block, local variable
- Thread: thread start/end
- Static: program start/end (static keyword has internal linkage)
- Dynamic: wherever by programmer

## Linkage

- No linkage: unique object instance (local variables)
- Internal linkage: referable in translation unit
- External linkage: referable from other translation units (normal, unqualified function)

## Implicit Conversion
More qualified -> implicit conversion

e.g.:
unqualified -> const
const -> const volatile

# Ex. 05

## `nm` Reference
### Usage:

- `-a`     Display  all symbol table entries, including those inserted for use by debuggers.
- `-p`     Don't sort; display in symbol-table order.

### Reference:

- `U` (undefined)
- `A` (absolute)
- `T` (text section symbol)
- `D` (data section symbol)
- `B` (bss section symbol)
- `C` (common symbol)
- `S` (symbol in a section other than those above)
- `I` (indirect symbol)

If the symbol is local (non-external) the symbol's type is instead represented by the corresponding lowercase letter. (I.e. A lower case u in a dynamic shared library indicates a undefined reference to a private external in another module in the same library.)


## Inspecting an `.o` file

### Compile with `gcc`

```
/usr/local/ex08/task5/g++-6 -c 05_01_storage_classes.cpp -o 05_01_storage_classes_cpp.o

/usr/local/ex08/task5/g++-6 -c 05_02_static_initialization.cpp -o 05_02_static_initialization_cpp.o
```


### Inspect with `nm`

```
nm -a -p 05_01_storage_classes_cpp.o | c++filt
```

```
0000000000000000 a 05_01_storage_classes.cpp
0000000000000000 t .text
0000000000000000 d .data
0000000000000000 b .bss
0000000000000004 b global2
0000000000000000 r .rodata
0000000000000000 r global3
0000000000000000 b .tbss
0000000000000004 b thread2
0000000000000008 b (anonymous namespace)::in_unnamed1
0000000000000007 t foo2()
000000000000000c b test()::local2
0000000000000000 n .note.GNU-stack
0000000000000000 r .eh_frame
0000000000000000 n .comment
0000000000000000 B global1
0000000000000000 B thread1
0000000000000000 T foo1()
000000000000000e T test()
0000000000000015 T main
```

---


```
nm -p 05_02_static_initialization_cpp.o | c++filt
```

```
0000000000000000 r std::piecewise_construct
0000000000000000 b std::__ioinit
0000000000000001 b foo()::a
0000000000000008 b guard variable for foo()::a
0000000000000197 t __static_initialization_and_destruction_0(int, int)
00000000000001d5 t _GLOBAL__sub_I__Z3foov
0000000000000000 n Trace::Trace()
0000000000000000 n Trace::~Trace()
0000000000000000 W Trace::Trace()
                 U std::cout
                 U std::basic_ostream<char, std::char_traits<char> >& std::operator<< <std::char_traits<char> >(std::basic_ostream<char, std::char_traits<char> >&, char const*)
                 U std::basic_ostream<char, std::char_traits<char> >::operator<<(void const*)
                 U std::basic_ostream<char, std::char_traits<char> >& std::endl<char, std::char_traits<char> >(std::basic_ostream<char, std::char_traits<char> >&)
                 U std::basic_ostream<char, std::char_traits<char> >::operator<<(std::basic_ostream<char, std::char_traits<char> >& (*)(std::basic_ostream<char, std::char_traits<char> >&))
0000000000000000 W Trace::Trace()
0000000000000000 W Trace::~Trace()
                 U __gxx_personality_v0
0000000000000000 W Trace::~Trace()
0000000000000000 T foo()
                 U __cxa_guard_acquire
                 U __cxa_guard_release
                 U __dso_handle
                 U __cxa_atexit
                 U __cxa_guard_abort
                 U _Unwind_Resume
                 U __stack_chk_fail
000000000000012e T main
                 U std::ios_base::Init::Init()
                 U std::ios_base::Init::~Init()
```


### Inspecting the `c` code
Only the first file was also converted to valid `.c` code.

```
/usr/local/ex08/task5/gcc-6 -c 05_01_storage_classes.c -o 05_01_storage_classes.o
```

```
nm -a -p 05_01_storage_classes.o | c++filt
```

```
0000000000000000 a 05_01_storage_classes.c
0000000000000000 t .text
0000000000000000 d .data
0000000000000000 b .bss
0000000000000000 b global2
0000000000000000 r .rodata
0000000000000007 t foo2
0000000000000004 b local2.1766
0000000000000000 n .note.GNU-stack
0000000000000000 r .eh_frame
0000000000000000 n .comment
0000000000000004 C global1
0000000000000000 R global3
0000000000000000 T foo1
000000000000000e T test
0000000000000015 T main
```

### Explanation


