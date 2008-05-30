#line 1 ".\\bench.c"
#line 1 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
#pragma once
#line 18 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
#line 25 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
typedef long time_t;         
#line 33 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
typedef __int64 __time64_t;
#line 36 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
#line 38 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
typedef unsigned short _ino_t;          
typedef unsigned short ino_t;
#line 48 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
#line 51 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
typedef unsigned int _dev_t;            
typedef unsigned int dev_t;
#line 61 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
#line 64 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
typedef long _off_t;                    
typedef long off_t;
#line 74 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
#line 77 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
#line 79 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\sys/types.h"
#line 2 ".\\bench.c"
#line 1 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#pragma once
#line 18 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 25 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#pragma pack(push,8)
#line 34 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 46 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 47 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 56 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 57 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 64 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
typedef __w64 unsigned int   size_t;
#line 72 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 74 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
typedef unsigned short wchar_t;
#line 80 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
typedef unsigned short wint_t;
typedef unsigned short wctype_t;
#line 87 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
typedef char *  va_list;
#line 98 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 100 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
struct _iobuf {
	char *_ptr;
	int   _cnt;
	char *_base;
	int   _flag;
	int   _file;
	int   _charbuf;
	int   _bufsiz;
	char *_tmpfname;
};
typedef struct _iobuf FILE;
#line 138 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 149 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 185 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 186 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
 extern FILE _iob[];
#line 193 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 204 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
typedef __int64 fpos_t;
#line 215 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 216 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 219 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
 int  _filbuf(FILE *);
 int  _flsbuf(int, FILE *);
 FILE *  _fsopen(const char *, const char *, int);
#line 255 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
 void  clearerr(FILE *);
 int  fclose(FILE *);
 int  _fcloseall(void);
 FILE *  _fdopen(int, const char *);
#line 265 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
 int  feof(FILE *);
 int  ferror(FILE *);
 int  fflush(FILE *);
 int  fgetc(FILE *);
 int  _fgetchar(void);
 int  fgetpos(FILE *, fpos_t *);
 char *  fgets(char *, int, FILE *);
 int  _fileno(FILE *);
#line 279 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
 int  _flushall(void);
 FILE *  fopen(const char *, const char *);
 int  fprintf(FILE *, const char *, ...);
 int  fputc(int, FILE *);
 int  _fputchar(int);
 int  fputs(const char *, FILE *);
 size_t  fread(void *, size_t, size_t, FILE *);
 FILE *  freopen(const char *, const char *, FILE *);
 int  fscanf(FILE *, const char *, ...);
 int  fsetpos(FILE *, const fpos_t *);
 int  fseek(FILE *, long, int);
 long  ftell(FILE *);
 size_t  fwrite(const void *, size_t, size_t, FILE *);
 int  getc(FILE *);
 int  getchar(void);
 int  _getmaxstdio(void);
 char *  gets(char *);
 int  _getw(FILE *);
 void  perror(const char *);
 int  _pclose(FILE *);
 FILE *  _popen(const char *, const char *);
 int  printf(const char *, ...);
 int  putc(int, FILE *);
 int  putchar(int);
 int  puts(const char *);
 int  _putw(int, FILE *);
 int  remove(const char *);
 int  rename(const char *, const char *);
 void  rewind(FILE *);
 int  _rmtmp(void);
 int  scanf(const char *, ...);
 void  setbuf(FILE *, char *);
 int  _setmaxstdio(int);
 int  setvbuf(FILE *, char *, int, size_t);
 int  _snprintf(char *, size_t, const char *, ...);
 int  sprintf(char *, const char *, ...);
 int  _scprintf(const char *, ...);
 int  sscanf(const char *, const char *, ...);
 int  _snscanf(const char *, size_t, const char *, ...);
 char *  _tempnam(const char *, const char *);
 FILE *  tmpfile(void);
 char *  tmpnam(char *);
 int  ungetc(int, FILE *);
 int  _unlink(const char *);
 int  vfprintf(FILE *, const char *, va_list);
 int  vprintf(const char *, va_list);
 int  _vsnprintf(char *, size_t, const char *, va_list);
 int  vsprintf(char *, const char *, va_list);
 int  _vscprintf(const char *, va_list);
#line 337 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
 FILE *  _wfsopen(const wchar_t *, const wchar_t *, int);
#line 343 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
 wint_t  fgetwc(FILE *);
 wint_t  _fgetwchar(void);
 wint_t  fputwc(wchar_t, FILE *);
 wint_t  _fputwchar(wchar_t);
 wint_t  getwc(FILE *);
 wint_t  getwchar(void);
 wint_t  putwc(wchar_t, FILE *);
 wint_t  putwchar(wchar_t);
 wint_t  ungetwc(wint_t, FILE *);
 wchar_t *  fgetws(wchar_t *, int, FILE *);
 int  fputws(const wchar_t *, FILE *);
 wchar_t *  _getws(wchar_t *);
 int  _putws(const wchar_t *);
 int  fwprintf(FILE *, const wchar_t *, ...);
 int  wprintf(const wchar_t *, ...);
 int  _snwprintf(wchar_t *, size_t, const wchar_t *, ...);
 int  swprintf(wchar_t *, const wchar_t *, ...);
 int  _scwprintf(const wchar_t *, ...);
 int  vfwprintf(FILE *, const wchar_t *, va_list);
 int  vwprintf(const wchar_t *, va_list);
 int  _vsnwprintf(wchar_t *, size_t, const wchar_t *, va_list);
 int  vswprintf(wchar_t *, const wchar_t *, va_list);
 int  _vscwprintf(const wchar_t *, va_list);
 int  fwscanf(FILE *, const wchar_t *, ...);
 int  swscanf(const wchar_t *, const wchar_t *, ...);
 int  _snwscanf(const wchar_t *, size_t, const wchar_t *, ...);
 int  wscanf(const wchar_t *, ...);
 FILE *  _wfdopen(int, const wchar_t *);
 FILE *  _wfopen(const wchar_t *, const wchar_t *);
 FILE *  _wfreopen(const wchar_t *, const wchar_t *, FILE *);
 void  _wperror(const wchar_t *);
 FILE *  _wpopen(const wchar_t *, const wchar_t *);
 int  _wremove(const wchar_t *);
 wchar_t *  _wtempnam(const wchar_t *, const wchar_t *);
 wchar_t *  _wtmpnam(wchar_t *);
#line 401 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 404 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 426 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
 int  fcloseall(void);
 FILE *  fdopen(int, const char *);
 int  fgetchar(void);
 int  fileno(FILE *);
 int  flushall(void);
 int  fputchar(int);
 int  getw(FILE *);
 int  putw(int, FILE *);
 int  rmtmp(void);
 char *  tempnam(const char *, const char *);
 int  unlink(const char *);
#line 449 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#pragma pack(pop)
#line 457 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 459 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdio.h"
#line 4 ".\\bench.c"
#line 1 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#pragma once
#line 19 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 26 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#pragma pack(push,8)
#line 35 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 48 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 65 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
typedef int ( * _onexit_t)(void);
#line 106 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 108 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
typedef struct _div_t {
	int quot;
	int rem;
} div_t;
typedef struct _ldiv_t {
	long quot;
	long rem;
} ldiv_t;
#line 126 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 extern int __mb_cur_max;
 int  ___mb_cur_max_func(void);
#line 142 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
typedef void ( * _secerr_handler_func)(int, void *);
#line 174 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
typedef void ( *_purecall_handler)(); 
 _purecall_handler  _set_purecall_handler(_purecall_handler);
 int *  _errno(void);
 unsigned long *  __doserrno(void);
#line 191 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 extern char * _sys_errlist[];   
 extern int _sys_nerr;           
#line 208 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 int *           __p___argc(void);
 char ***        __p___argv(void);
 wchar_t ***     __p___wargv(void);
 char ***        __p__environ(void);
 wchar_t ***     __p__wenviron(void);
 char **         __p__pgmptr(void);
 wchar_t **      __p__wpgmptr(void);
#line 237 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 extern int _fmode;          
 extern int _fileinfo;       
 extern unsigned int _osplatform;
 extern unsigned int _osver;
 extern unsigned int _winver;
 extern unsigned int _winmajor;
 extern unsigned int _winminor;
 void    abort(void);
 void    exit(int);
#line 261 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 265 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
int     abs(int);
#line 267 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
__int64     _abs64(__int64);
int     atexit(void ( *)(void));
 double  atof(const char *);
 int     atoi(const char *);
 long    atol(const char *);
 void *  bsearch(const void *, const void *, size_t, size_t,
											 int ( *)(const void *, const void *));
unsigned short  _byteswap_ushort(unsigned short);
unsigned long   _byteswap_ulong (unsigned long);
unsigned __int64  _byteswap_uint64(unsigned __int64);
 void *  calloc(size_t, size_t);
 div_t   div(int, int);
 void    free(void *);
 char *  getenv(const char *);
 char *  _itoa(int, char *, int);
 char *  _i64toa(__int64, char *, int);
 char *  _ui64toa(unsigned __int64, char *, int);
 __int64  _atoi64(const char *);
 __int64  _strtoi64(const char *, char **, int);
 unsigned __int64  _strtoui64(const char *, char **, int);
#line 289 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 292 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
long  labs(long);
#line 294 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 ldiv_t  ldiv(long, long);
 char *  _ltoa(long, char *, int);
 void *  malloc(size_t);
 int     mblen(const char *, size_t);
 size_t  _mbstrlen(const char *s);
 int     mbtowc(wchar_t *, const char *, size_t);
 size_t  mbstowcs(wchar_t *, const char *, size_t);
 void    qsort(void *, size_t, size_t, int ( *)
										   (const void *, const void *));
 int     rand(void);
 void *  realloc(void *, size_t);
 int     _set_error_mode(int);
 _secerr_handler_func
 _set_security_error_handler(_secerr_handler_func);
#line 310 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 void    srand(unsigned int);
 double  strtod(const char *, char **);
 long    strtol(const char *, char **, int);
 unsigned long  strtoul(const char *, char **, int);
 int     system(const char *);
 char *  _ultoa(unsigned long, char *, int);
 int     wctomb(char *, wchar_t);
 size_t  wcstombs(char *, const wchar_t *, size_t);
 wchar_t *  _itow (int, wchar_t *, int);
 wchar_t *  _ltow (long, wchar_t *, int);
 wchar_t *  _ultow (unsigned long, wchar_t *, int);
 double  wcstod(const wchar_t *, wchar_t **);
 long    wcstol(const wchar_t *, wchar_t **, int);
 unsigned long  wcstoul(const wchar_t *, wchar_t **, int);
 wchar_t *  _wgetenv(const wchar_t *);
 int     _wsystem(const wchar_t *);
 double  _wtof(const wchar_t *);
 int  _wtoi(const wchar_t *);
 long  _wtol(const wchar_t *);
 wchar_t *  _i64tow(__int64, wchar_t *, int);
 wchar_t *  _ui64tow(unsigned __int64, wchar_t *, int);
 __int64    _wtoi64(const wchar_t *);
 __int64    _wcstoi64(const wchar_t *, wchar_t **, int);
 unsigned __int64   _wcstoui64(const wchar_t *, wchar_t **, int);
#line 342 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 345 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 char *  _ecvt(double, int, int *, int *);
  void    _exit(int);
#line 360 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 char *  _fcvt(double, int, int *, int *);
 char *  _fullpath(char *, const char *, size_t);
 char *  _gcvt(double, int, char *);
unsigned long  _lrotl(unsigned long, int);
unsigned long  _lrotr(unsigned long, int);
 void    _makepath(char *, const char *, const char *, const char *,
											   const char *);
_onexit_t  _onexit(_onexit_t);
 void    perror(const char *);
 int     _putenv(const char *);
unsigned int  _rotl(unsigned int, int);
unsigned __int64  _rotl64(unsigned __int64, int);
unsigned int  _rotr(unsigned int, int);
unsigned __int64  _rotr64(unsigned __int64, int);
 void    _searchenv(const char *, const char *, char *);
 void    _splitpath(const char *, char *, char *, char *, char *);
 void    _swab(char *, char *, int);
 wchar_t *  _wfullpath(wchar_t *, const wchar_t *, size_t);
 void    _wmakepath(wchar_t *, const wchar_t *, const wchar_t *, const wchar_t *,
												const wchar_t *);
 void    _wperror(const wchar_t *);
 int     _wputenv(const wchar_t *);
 void    _wsearchenv(const wchar_t *, const wchar_t *, wchar_t *);
 void    _wsplitpath(const wchar_t *, wchar_t *, wchar_t *, wchar_t *, wchar_t *);
#line 392 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 void  _seterrormode(int);
 void  _beep(unsigned, unsigned);
 void  _sleep(unsigned long);
#line 401 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 int  tolower(int);
#line 410 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 int  toupper(int);
#line 413 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 415 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 427 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
 char *  ecvt(double, int, int *, int *);
 char *  fcvt(double, int, int *, int *);
 char *  gcvt(double, int, char *);
 char *  itoa(int, char *, int);
 char *  ltoa(long, char *, int);
_onexit_t  onexit(_onexit_t);
 int     putenv(const char *);
 void    swab(char *, char *, int);
 char *  ultoa(unsigned long, char *, int);
#line 443 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 445 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#pragma pack(pop)
#line 454 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 456 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stdlib.h"
#line 5 ".\\bench.c"
#line 1 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
#pragma once
#line 18 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
#line 25 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
#line 39 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
#line 55 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
#line 76 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"

void *   memcpy(void *, const void *, size_t);
int      memcmp(const void *, const void *, size_t);
void *   memset(void *, int, size_t);
char *   _strset(char *, int);
char *   strcpy(char *, const char *);
char *   strcat(char *, const char *);
int      strcmp(const char *, const char *);
size_t   strlen(const char *);
#line 109 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
 void *   _memccpy(void *, const void *, int, size_t);
 void *   memchr(const void *, int, size_t);
 int      _memicmp(const void *, const void *, size_t);
#line 116 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
 void *   memmove(void *, const void *, size_t);
#line 118 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
 char *   strchr(const char *, int);
 int      _strcmpi(const char *, const char *);
 int      _stricmp(const char *, const char *);
 int      strcoll(const char *, const char *);
 int      _stricoll(const char *, const char *);
 int      _strncoll(const char *, const char *, size_t);
 int      _strnicoll(const char *, const char *, size_t);
 size_t   strcspn(const char *, const char *);
 char *   _strdup(const char *);
 char *   _strerror(const char *);
 char *   strerror(int);
 char *   _strlwr(char *);
 char *   strncat(char *, const char *, size_t);
 int      strncmp(const char *, const char *, size_t);
 int      _strnicmp(const char *, const char *, size_t);
 char *   strncpy(char *, const char *, size_t);
 char *   _strnset(char *, int, size_t);
 char *   strpbrk(const char *, const char *);
 char *   strrchr(const char *, int);
 char *   _strrev(char *);
 size_t   strspn(const char *, const char *);
 char *   strstr(const char *, const char *);
 char *   strtok(char *, const char *);
 char *   _strupr(char *);
 size_t   strxfrm (char *, const char *, size_t);
 void *  memccpy(void *, const void *, int, size_t);
 int  memicmp(const void *, const void *, size_t);
 int  strcmpi(const char *, const char *);
 int  stricmp(const char *, const char *);
 char *  strdup(const char *);
 char *  strlwr(char *);
 int  strnicmp(const char *, const char *, size_t);
 char *  strnset(char *, int, size_t);
 char *  strrev(char *);
char *  strset(char *, int);
 char *  strupr(char *);
#line 163 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
 wchar_t *  wcscat(wchar_t *, const wchar_t *);
 wchar_t *  wcschr(const wchar_t *, wchar_t);
 int  wcscmp(const wchar_t *, const wchar_t *);
 wchar_t *  wcscpy(wchar_t *, const wchar_t *);
 size_t  wcscspn(const wchar_t *, const wchar_t *);
 size_t  wcslen(const wchar_t *);
 wchar_t *  wcsncat(wchar_t *, const wchar_t *, size_t);
 int  wcsncmp(const wchar_t *, const wchar_t *, size_t);
 wchar_t *  wcsncpy(wchar_t *, const wchar_t *, size_t);
 wchar_t *  wcspbrk(const wchar_t *, const wchar_t *);
 wchar_t *  wcsrchr(const wchar_t *, wchar_t);
 size_t  wcsspn(const wchar_t *, const wchar_t *);
 wchar_t *  wcsstr(const wchar_t *, const wchar_t *);
 wchar_t *  wcstok(wchar_t *, const wchar_t *);
 wchar_t *  _wcserror(int);
 wchar_t *  __wcserror(const wchar_t *);
 wchar_t *  _wcsdup(const wchar_t *);
 int  _wcsicmp(const wchar_t *, const wchar_t *);
 int  _wcsnicmp(const wchar_t *, const wchar_t *, size_t);
 wchar_t *  _wcsnset(wchar_t *, wchar_t, size_t);
 wchar_t *  _wcsrev(wchar_t *);
 wchar_t *  _wcsset(wchar_t *, wchar_t);
 wchar_t *  _wcslwr(wchar_t *);
 wchar_t *  _wcsupr(wchar_t *);
 size_t  wcsxfrm(wchar_t *, const wchar_t *, size_t);
 int  wcscoll(const wchar_t *, const wchar_t *);
 int  _wcsicoll(const wchar_t *, const wchar_t *);
 int  _wcsncoll(const wchar_t *, const wchar_t *, size_t);
 int  _wcsnicoll(const wchar_t *, const wchar_t *, size_t);
 wchar_t *  wcsdup(const wchar_t *);
 int  wcsicmp(const wchar_t *, const wchar_t *);
 int  wcsnicmp(const wchar_t *, const wchar_t *, size_t);
 wchar_t *  wcsnset(wchar_t *, wchar_t, size_t);
 wchar_t *  wcsrev(wchar_t *);
 wchar_t *  wcsset(wchar_t *, wchar_t);
 wchar_t *  wcslwr(wchar_t *);
 wchar_t *  wcsupr(wchar_t *);
 int  wcsicoll(const wchar_t *, const wchar_t *);
#line 218 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
#line 221 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
#line 228 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\string.h"
#line 6 ".\\bench.c"
#line 1 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
#pragma once
#line 17 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
#line 24 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
#pragma pack(push,8)
#line 33 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
#line 46 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
#line 63 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
struct _diskfree_t {
	unsigned total_clusters;
	unsigned avail_clusters;
	unsigned sectors_per_cluster;
	unsigned bytes_per_sector;
};
#line 90 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
 int  _chdir(const char *);
 char *  _getcwd(char *, int);
 int  _mkdir(const char *);
 int  _rmdir(const char *);
 int  _chdrive(int);
 char *  _getdcwd(int, char *, int);
 int  _getdrive(void);
 unsigned long  _getdrives(void);
 unsigned  _getdiskfree(unsigned, struct _diskfree_t *);
 int  _wchdir(const wchar_t *);
 wchar_t *  _wgetcwd(wchar_t *, int);
 wchar_t *  _wgetdcwd(int, wchar_t *, int);
 int  _wmkdir(const wchar_t *);
 int  _wrmdir(const wchar_t *);
#line 116 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
 int  chdir(const char *);
 char *  getcwd(char *, int);
 int  mkdir(const char *);
 int  rmdir(const char *);
#line 130 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
#pragma pack(pop)
#line 138 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
#line 140 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\direct.h"
#line 9 ".\\bench.c"
extern int getopt(int, char * const *, const char *);
#line 13 ".\\bench.c"
#line 1 "..\\build_win32\\db.h"
#line 1 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#pragma once
#line 18 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#line 25 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#line 39 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#line 55 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
 extern int *  _errno(void);
#line 76 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
typedef __w64 int            intptr_t;
#line 86 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#line 88 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
typedef __w64 unsigned int   uintptr_t;
#line 95 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#line 97 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
typedef __w64 int            ptrdiff_t;
#line 104 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#line 106 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#line 130 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
 extern unsigned long   __threadid(void);
 extern uintptr_t  __threadhandle(void);
#line 137 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#line 144 "C:\\Programme\\Microsoft Visual Studio .NET 2003\\Vc7\\include\\stddef.h"
#line 28 "..\\build_win32\\db.h"
#line 30 "..\\build_win32\\db.h"
#line 34 "..\\build_win32\\db.h"
typedef unsigned char u_int8_t;
typedef short int16_t;
typedef unsigned short u_int16_t;
typedef int int32_t;
typedef unsigned int u_int32_t;
typedef __int64 int64_t;
typedef unsigned __int64 u_int64_t;
#line 70 "..\\build_win32\\db.h"
typedef unsigned char u_char;
typedef unsigned short u_short;
typedef unsigned int u_int;
typedef unsigned long u_long;
#line 77 "..\\build_win32\\db.h"
typedef int32_t ssize_t;
#line 82 "..\\build_win32\\db.h"
typedef u_int64_t uintmax_t;
typedef u_int32_t uintptr_t;
#line 105 "..\\build_win32\\db.h"
typedef int64_t db_seq_t;
typedef u_int32_t db_threadid_t;
typedef int pid_t;
typedef u_int32_t db_pgno_t; 
typedef u_int16_t db_indx_t; 
typedef u_int32_t db_recno_t; 
typedef u_int32_t db_timeout_t; 
typedef uintptr_t roff_t;
struct __db;  typedef struct __db DB;
struct __db_bt_stat; typedef struct __db_bt_stat DB_BTREE_STAT;
struct __db_cipher; typedef struct __db_cipher DB_CIPHER;
struct __db_compact; typedef struct __db_compact DB_COMPACT;
struct __db_dbt; typedef struct __db_dbt DBT;
struct __db_env; typedef struct __db_env DB_ENV;
struct __db_h_stat; typedef struct __db_h_stat DB_HASH_STAT;
struct __db_ilock; typedef struct __db_ilock DB_LOCK_ILOCK;
struct __db_lock_stat; typedef struct __db_lock_stat DB_LOCK_STAT;
struct __db_lock_u; typedef struct __db_lock_u DB_LOCK;
struct __db_lockreq; typedef struct __db_lockreq DB_LOCKREQ;
struct __db_log_cursor; typedef struct __db_log_cursor DB_LOGC;
struct __db_log_stat; typedef struct __db_log_stat DB_LOG_STAT;
struct __db_lsn; typedef struct __db_lsn DB_LSN;
struct __db_mpool; typedef struct __db_mpool DB_MPOOL;
struct __db_mpool_fstat;typedef struct __db_mpool_fstat DB_MPOOL_FSTAT;
struct __db_mpool_stat; typedef struct __db_mpool_stat DB_MPOOL_STAT;
struct __db_mpoolfile; typedef struct __db_mpoolfile DB_MPOOLFILE;
struct __db_mutex_stat; typedef struct __db_mutex_stat DB_MUTEX_STAT;
struct __db_preplist; typedef struct __db_preplist DB_PREPLIST;
struct __db_qam_stat; typedef struct __db_qam_stat DB_QUEUE_STAT;
struct __db_rep; typedef struct __db_rep DB_REP;
struct __db_rep_stat; typedef struct __db_rep_stat DB_REP_STAT;
struct __db_seq_record; typedef struct __db_seq_record DB_SEQ_RECORD;
struct __db_seq_stat; typedef struct __db_seq_stat DB_SEQUENCE_STAT;
struct __db_sequence; typedef struct __db_sequence DB_SEQUENCE;
struct __db_txn; typedef struct __db_txn DB_TXN;
struct __db_txn_active; typedef struct __db_txn_active DB_TXN_ACTIVE;
struct __db_txn_stat; typedef struct __db_txn_stat DB_TXN_STAT;
struct __db_txnmgr; typedef struct __db_txnmgr DB_TXNMGR;
struct __dbc;  typedef struct __dbc DBC;
struct __dbc_internal; typedef struct __dbc_internal DBC_INTERNAL;
struct __fh_t;  typedef struct __fh_t DB_FH;
struct __fname;  typedef struct __fname FNAME;
struct __key_range; typedef struct __key_range DB_KEY_RANGE;
struct __mpoolfile; typedef struct __mpoolfile MPOOLFILE;
struct __db_dbt { void  *data;    u_int32_t size;   
u_int32_t ulen;    u_int32_t dlen;    u_int32_t doff;   
u_int32_t flags;
};
typedef u_int32_t db_mutex_t;
struct __db_mutex_stat {  u_int32_t st_mutex_align;  u_int32_t st_mutex_tas_spins;  u_int32_t st_mutex_cnt;   u_int32_t st_mutex_free;  u_int32_t st_mutex_inuse;  u_int32_t st_mutex_inuse_max; 
u_int32_t st_region_wait;  u_int32_t st_region_nowait;  roff_t   st_regsize;  
};
typedef enum { DB_LOCK_NG=0,    DB_LOCK_READ=1,    DB_LOCK_WRITE=2,   DB_LOCK_WAIT=3,    DB_LOCK_IWRITE=4,   DB_LOCK_IREAD=5,   DB_LOCK_IWR=6,    DB_LOCK_READ_UNCOMMITTED=7,  DB_LOCK_WWRITE=8  
} db_lockmode_t;
typedef enum { DB_LOCK_DUMP=0,    DB_LOCK_GET=1,    DB_LOCK_GET_TIMEOUT=2,   DB_LOCK_INHERIT=3,   DB_LOCK_PUT=4,    DB_LOCK_PUT_ALL=5,   DB_LOCK_PUT_OBJ=6,   DB_LOCK_PUT_READ=7,   DB_LOCK_TIMEOUT=8,   DB_LOCK_TRADE=9,   DB_LOCK_UPGRADE_WRITE=10 
} db_lockop_t;
typedef enum  { DB_LSTAT_ABORTED=1,   DB_LSTAT_EXPIRED=2,   DB_LSTAT_FREE=3,   DB_LSTAT_HELD=4,   DB_LSTAT_PENDING=5,  
DB_LSTAT_WAITING=6  
}db_status_t;
struct __db_lock_stat { u_int32_t st_id;   u_int32_t st_cur_maxid;   u_int32_t st_maxlocks;   u_int32_t st_maxlockers;  u_int32_t st_maxobjects;  int   st_nmodes;   u_int32_t st_nlocks;   u_int32_t st_maxnlocks;   u_int32_t st_nlockers;   u_int32_t st_maxnlockers;  u_int32_t st_nobjects;   u_int32_t st_maxnobjects;  u_int32_t st_nrequests;   u_int32_t st_nreleases;   u_int32_t st_nupgrade;   u_int32_t st_ndowngrade;  u_int32_t st_lock_wait;   u_int32_t st_lock_nowait;  u_int32_t st_ndeadlocks;  db_timeout_t st_locktimeout;  u_int32_t st_nlocktimeouts;  db_timeout_t st_txntimeout;  u_int32_t st_ntxntimeouts;  u_int32_t st_region_wait;  u_int32_t st_region_nowait;  roff_t   st_regsize;  
};
struct __db_ilock { db_pgno_t pgno;    u_int8_t fileid[20];
u_int32_t type;   
};
struct __db_lock_u { roff_t  off;   u_int32_t ndx;  
u_int32_t gen;   db_lockmode_t mode;  
};
struct __db_lockreq { db_lockop_t  op;   db_lockmode_t  mode;   db_timeout_t  timeout;  DBT  *obj;   DB_LOCK   lock;  
};
struct __db_lsn { u_int32_t file;   u_int32_t offset;  
};
struct __db_log_cursor { DB_ENV  *dbenv;  
DB_FH  *c_fhp;   DB_LSN   c_lsn;   u_int32_t c_len;   u_int32_t c_prev;  
DBT   c_dbt;  
u_int8_t *bp;    u_int32_t bp_size;   u_int32_t bp_rlen;   DB_LSN   bp_lsn;  
u_int32_t bp_maxrec;  

int (*close) (DB_LOGC *, u_int32_t); 

int (*get) (DB_LOGC *, DB_LSN *, DBT *, u_int32_t); 
u_int32_t flags;
};
struct __db_log_stat { u_int32_t st_magic;   u_int32_t st_version;   int   st_mode;   u_int32_t st_lg_bsize;   u_int32_t st_lg_size;   u_int32_t st_record;   u_int32_t st_w_bytes;   u_int32_t st_w_mbytes;   u_int32_t st_wc_bytes;   u_int32_t st_wc_mbytes;   u_int32_t st_wcount;   u_int32_t st_wcount_fill;  u_int32_t st_rcount;   u_int32_t st_scount;   u_int32_t st_region_wait;  u_int32_t st_region_nowait;  u_int32_t st_cur_file;   u_int32_t st_cur_offset;  u_int32_t st_disk_file;   u_int32_t st_disk_offset;  roff_t   st_regsize;   u_int32_t st_maxcommitperflush;  u_int32_t st_mincommitperflush; 
};
typedef enum { DB_PRIORITY_VERY_LOW=1, DB_PRIORITY_LOW=2, DB_PRIORITY_DEFAULT=3, DB_PRIORITY_HIGH=4, DB_PRIORITY_VERY_HIGH=5
} DB_CACHE_PRIORITY;
struct __db_mpoolfile { DB_FH   *fhp;   

u_int32_t  ref;   
u_int32_t pinref;  

struct {  struct __db_mpoolfile *tqe_next;  struct __db_mpoolfile **tqe_prev; } q;    

DB_ENV        *dbenv;   MPOOLFILE      *mfp;  
u_int32_t clear_len;  u_int8_t      fileid[20]; int  ftype;   int32_t  lsn_offset;  u_int32_t gbytes, bytes;  DBT        *pgcookie;  int32_t  priority; 
void        *addr;   size_t  len;  
u_int32_t config_flags; 
int (*close) (DB_MPOOLFILE *, u_int32_t); int (*get) (DB_MPOOLFILE *, db_pgno_t *, u_int32_t, void *); int (*open) (DB_MPOOLFILE *, const char *, u_int32_t, int, size_t); int (*put) (DB_MPOOLFILE *, void *, u_int32_t); int (*set) (DB_MPOOLFILE *, void *, u_int32_t); int (*get_clear_len) (DB_MPOOLFILE *, u_int32_t *); int (*set_clear_len) (DB_MPOOLFILE *, u_int32_t); int (*get_fileid) (DB_MPOOLFILE *, u_int8_t *); int (*set_fileid) (DB_MPOOLFILE *, u_int8_t *); int (*get_flags) (DB_MPOOLFILE *, u_int32_t *); int (*set_flags) (DB_MPOOLFILE *, u_int32_t, int); int (*get_ftype) (DB_MPOOLFILE *, int *); int (*set_ftype) (DB_MPOOLFILE *, int); int (*get_lsn_offset) (DB_MPOOLFILE *, int32_t *); int (*set_lsn_offset) (DB_MPOOLFILE *, int32_t); int (*get_maxsize) (DB_MPOOLFILE *, u_int32_t *, u_int32_t *); int (*set_maxsize) (DB_MPOOLFILE *, u_int32_t, u_int32_t); int (*get_pgcookie) (DB_MPOOLFILE *, DBT *); int (*set_pgcookie) (DB_MPOOLFILE *, DBT *); int (*get_priority) (DB_MPOOLFILE *, DB_CACHE_PRIORITY *); int (*set_priority) (DB_MPOOLFILE *, DB_CACHE_PRIORITY); int (*sync) (DB_MPOOLFILE *); 

u_int32_t  flags;
};
struct __db_mpool_stat { u_int32_t st_gbytes;   u_int32_t st_bytes;   u_int32_t st_ncache;   roff_t   st_regsize;   size_t   st_mmapsize;   int   st_maxopenfd;   int   st_maxwrite;   int   st_maxwrite_sleep;  u_int32_t st_map;   u_int32_t st_cache_hit;   u_int32_t st_cache_miss;  u_int32_t st_page_create;  u_int32_t st_page_in;   u_int32_t st_page_out;   u_int32_t st_ro_evict;   u_int32_t st_rw_evict;   u_int32_t st_page_trickle;  u_int32_t st_pages;   u_int32_t st_page_clean;  u_int32_t st_page_dirty;  u_int32_t st_hash_buckets;  u_int32_t st_hash_searches;  u_int32_t st_hash_longest;  u_int32_t st_hash_examined;  u_int32_t st_hash_nowait;  u_int32_t st_hash_wait;   u_int32_t st_hash_max_wait;  u_int32_t st_region_nowait;  u_int32_t st_region_wait;  u_int32_t st_alloc;   u_int32_t st_alloc_buckets;  u_int32_t st_alloc_max_buckets;  u_int32_t st_alloc_pages;  u_int32_t st_alloc_max_pages; 
};
struct __db_mpool_fstat { char *file_name;   u_int32_t st_pagesize;   u_int32_t st_map;   u_int32_t st_cache_hit;   u_int32_t st_cache_miss;  u_int32_t st_page_create;  u_int32_t st_page_in;   u_int32_t st_page_out;  
};
typedef enum { DB_TXN_ABORT=0,    DB_TXN_APPLY=1,    DB_TXN_BACKWARD_ALLOC=2,  DB_TXN_BACKWARD_ROLL=3,   DB_TXN_FORWARD_ROLL=4,   DB_TXN_OPENFILES=5,   DB_TXN_POPENFILES=6,   DB_TXN_PRINT=7   
} db_recops;
struct __db_txn { DB_TXNMGR *mgrp;   DB_TXN  *parent; 
u_int32_t txnid;   char  *name;  
db_threadid_t tid;   void  *td;   db_timeout_t lock_timeout;  db_timeout_t expire;   void  *txn_list; 

struct {  struct __db_txn *tqe_next;  struct __db_txn **tqe_prev; } links;    struct {  struct __db_txn *tqe_next;  struct __db_txn **tqe_prev; } xalinks;   

struct __kids {  struct __db_txn *tqh_first;  struct __db_txn **tqh_last; } kids;

struct {  struct __txn_event *tqh_first;  struct __txn_event **tqh_last; } events;

struct {  struct __txn_logrec *stqh_first;  struct __txn_logrec **stqh_last; } logs;    

struct {  struct __db_txn *tqe_next;  struct __db_txn **tqe_prev; } klinks;
void *api_internal;   void *xml_internal;  
u_int32_t cursors; 
int   (*abort) (DB_TXN *); int   (*commit) (DB_TXN *, u_int32_t); int   (*discard) (DB_TXN *, u_int32_t); int   (*get_name) (DB_TXN *, const char **); u_int32_t (*id) (DB_TXN *); int   (*prepare) (DB_TXN *, u_int8_t *); int   (*set_name) (DB_TXN *, const char *); int   (*set_timeout) (DB_TXN *, db_timeout_t, u_int32_t); 
void   (*set_txn_lsnp) (DB_TXN *txn, DB_LSN **, DB_LSN **); 
u_int32_t flags;
};
struct __db_preplist { DB_TXN *txn; u_int8_t gid[128];
};
struct __db_txn_active { u_int32_t txnid;   u_int32_t parentid;   pid_t     pid;    db_threadid_t tid;   DB_LSN   lsn;    u_int32_t xa_status;   u_int8_t  xid[128];  char   name[51];  
};
struct __db_txn_stat { DB_LSN   st_last_ckp;   time_t   st_time_ckp;   u_int32_t st_last_txnid;  u_int32_t st_maxtxns;   u_int32_t st_naborts;   u_int32_t st_nbegins;   u_int32_t st_ncommits;   u_int32_t st_nactive;   u_int32_t st_nrestores;  
u_int32_t st_maxnactive;  DB_TXN_ACTIVE *st_txnarray;  u_int32_t st_region_wait;  u_int32_t st_region_nowait;  roff_t   st_regsize;  
};
struct __db_rep_stat { 
	u_int32_t st_status;   DB_LSN st_next_lsn;   DB_LSN st_waiting_lsn;   db_pgno_t st_next_pg;   db_pgno_t st_waiting_pg; 
	u_int32_t st_dupmasters; 
	int st_env_id;    int st_env_priority;   u_int32_t st_bulk_fills;  u_int32_t st_bulk_overflows;  u_int32_t st_bulk_records;  u_int32_t st_bulk_transfers;  u_int32_t st_client_rerequests;  u_int32_t st_client_svc_req; 
	u_int32_t st_client_svc_miss; 
	u_int32_t st_gen;   u_int32_t st_egen;   u_int32_t st_log_duplicated;  u_int32_t st_log_queued;  u_int32_t st_log_queued_max;  u_int32_t st_log_queued_total;  u_int32_t st_log_records;  u_int32_t st_log_requested;  int st_master;    u_int32_t st_master_changes;  u_int32_t st_msgs_badgen;  u_int32_t st_msgs_processed;  u_int32_t st_msgs_recover; 
	u_int32_t st_msgs_send_failures; u_int32_t st_msgs_sent;   u_int32_t st_newsites;   int st_nsites;   
	u_int32_t st_nthrottles;  u_int32_t st_outdated;  
	u_int32_t st_pg_duplicated;  u_int32_t st_pg_records;  u_int32_t st_pg_requested;  u_int32_t st_startup_complete;  u_int32_t st_txns_applied; 
	u_int32_t st_elections;   u_int32_t st_elections_won; 
	int st_election_cur_winner;  u_int32_t st_election_gen;  DB_LSN st_election_lsn;   int st_election_nsites;   int st_election_nvotes;   int st_election_priority;  int st_election_status;   u_int32_t st_election_tiebreaker; int st_election_votes;   u_int32_t st_election_sec;  u_int32_t st_election_usec; 
};
struct __db_seq_record { u_int32_t seq_version; 
u_int32_t flags;   db_seq_t seq_value;  db_seq_t seq_max;  db_seq_t seq_min; 
};
struct __db_sequence { DB  *seq_dbp;  db_mutex_t mtx_seq;  DB_SEQ_RECORD *seq_rp;  DB_SEQ_RECORD seq_record;  int32_t  seq_cache_size;  db_seq_t seq_last_value;  DBT  seq_key;  DBT  seq_data; 
void  *api_internal;
int  (*close) (DB_SEQUENCE *, u_int32_t); int  (*get) (DB_SEQUENCE *, DB_TXN *, int32_t, db_seq_t *, u_int32_t); int  (*get_cachesize) (DB_SEQUENCE *, int32_t *); int  (*get_db) (DB_SEQUENCE *, DB **); int  (*get_flags) (DB_SEQUENCE *, u_int32_t *); int  (*get_key) (DB_SEQUENCE *, DBT *); int  (*get_range) (DB_SEQUENCE *, db_seq_t *, db_seq_t *); int  (*initial_value) (DB_SEQUENCE *, db_seq_t); int  (*open) (DB_SEQUENCE *, DB_TXN *, DBT *, u_int32_t); int  (*remove) (DB_SEQUENCE *, DB_TXN *, u_int32_t); int  (*set_cachesize) (DB_SEQUENCE *, int32_t); int  (*set_flags) (DB_SEQUENCE *, u_int32_t); int  (*set_range) (DB_SEQUENCE *, db_seq_t, db_seq_t); int  (*stat) (DB_SEQUENCE *, DB_SEQUENCE_STAT **, u_int32_t); int  (*stat_print) (DB_SEQUENCE *, u_int32_t); 
};
struct __db_seq_stat { u_int32_t st_wait;   u_int32_t st_nowait;   db_seq_t  st_current;   db_seq_t  st_value;   db_seq_t  st_last_value;  db_seq_t  st_min;   db_seq_t  st_max;   int32_t   st_cache_size;  u_int32_t st_flags;  
};
typedef enum { DB_BTREE=1, DB_HASH=2, DB_RECNO=3, DB_QUEUE=4, DB_UNKNOWN=5   
} DBTYPE;
struct __db { 
	u_int32_t pgsize;  
	int (*db_append_recno) (DB *, DBT *, db_recno_t); void (*db_feedback) (DB *, int, int); int (*dup_compare) (DB *, const DBT *, const DBT *);
	void *app_private;  

	DB_ENV *dbenv;   
	DBTYPE  type;   
	DB_MPOOLFILE *mpf;  
	db_mutex_t mutex;  
	char *fname, *dname;   u_int32_t open_flags;  
	u_int8_t fileid[20];
	u_int32_t adj_fileid;  
	FNAME *log_filename;  
	db_pgno_t meta_pgno;   u_int32_t lid;    u_int32_t cur_lid;   u_int32_t associate_lid;  DB_LOCK  handle_lock;  
	u_int  cl_id;   
	time_t  timestamp;   u_int32_t fid_gen;  

	DBT  my_rskey;   DBT  my_rkey;   DBT  my_rdata;  

	DB_FH *saved_open_fhp; 

	struct {  struct __db *le_next;  struct __db **le_prev; } dblistlinks;

	struct __cq_fq {  struct __dbc *tqh_first;  struct __dbc **tqh_last; } free_queue; struct __cq_aq {  struct __dbc *tqh_first;  struct __dbc **tqh_last; } active_queue; struct __cq_jq {  struct __dbc *tqh_first;  struct __dbc **tqh_last; } join_queue;

	struct {  struct __db *lh_first; } s_secondaries;

	struct {  struct __db *le_next;  struct __db **le_prev; } s_links; u_int32_t s_refcnt;
	int (*s_callback) (DB *, const DBT *, const DBT *, DBT *);
	DB *s_primary;
	u_int32_t s_assoc_flags;
	void *api_internal;
	void *bt_internal;   void *h_internal;   void *q_internal;   void *xa_internal;  
	//int  (*associate) (DB *, DB_TXN *, DB *, int (*)(DB *, const DBT *, const DBT *, DBT *), u_int32_t); int  (*close) (DB *, u_int32_t); int  (*compact) (DB *, DB_TXN *, DBT *, DBT *, DB_COMPACT *, u_int32_t, DBT *); int  (*cursor) (DB *, DB_TXN *, DBC **, u_int32_t); int  (*del) (DB *, DB_TXN *, DBT *, u_int32_t); void (*err) (DB *, int, const char *, ...); void (*errx) (DB *, const char *, ...); int  (*fd) (DB *, int *); int  (*get) (DB *, DB_TXN *, DBT *, DBT *, u_int32_t); int  (*get_bt_minkey) (DB *, u_int32_t *); int  (*get_byteswapped) (DB *, int *); int  (*get_cachesize) (DB *, u_int32_t *, u_int32_t *, int *); int  (*get_dbname) (DB *, const char **, const char **); int  (*get_encrypt_flags) (DB *, u_int32_t *); DB_ENV *(*get_env) (DB *); void (*get_errfile) (DB *, FILE **); void (*get_errpfx) (DB *, const char **); int  (*get_flags) (DB *, u_int32_t *); int  (*get_h_ffactor) (DB *, u_int32_t *); int  (*get_h_nelem) (DB *, u_int32_t *); int  (*get_lorder) (DB *, int *); DB_MPOOLFILE *(*get_mpf) (DB *); void (*get_msgfile) (DB *, FILE **); int  (*get_open_flags) (DB *, u_int32_t *); int  (*get_pagesize) (DB *, u_int32_t *); int  (*get_q_extentsize) (DB *, u_int32_t *); int  (*get_re_delim) (DB *, int *); int  (*get_re_len) (DB *, u_int32_t *); int  (*get_re_pad) (DB *, int *); int  (*get_re_source) (DB *, const char **); int  (*get_transactional) (DB *); int  (*get_type) (DB *, DBTYPE *); int  (*join) (DB *, DBC **, DBC **, u_int32_t); int  (*key_range)  (DB *, DB_TXN *, DBT *, DB_KEY_RANGE *, u_int32_t); int  (*open) (DB *, DB_TXN *, const char *, const char *, DBTYPE, u_int32_t, int); int  (*pget) (DB *, DB_TXN *, DBT *, DBT *, DBT *, u_int32_t); int  (*put) (DB *, DB_TXN *, DBT *, DBT *, u_int32_t); int  (*remove) (DB *, const char *, const char *, u_int32_t); int  (*rename) (DB *, const char *, const char *, const char *, u_int32_t); int  (*set_alloc) (DB *, void *(*)(size_t), void *(*)(void *, size_t), void (*)(void *)); int  (*set_append_recno) (DB *, int (*)(DB *, DBT *, db_recno_t)); int  (*set_bt_
	//	compare)  (DB *, int (*)(DB *, const DBT *, const DBT *)); int  (*set_bt_minkey) (DB *, u_int32_t); int  (*set_bt_prefix)  (DB *, size_t (*)(DB *, const DBT *, const DBT *)); int  (*set_cachesize) (DB *, u_int32_t, u_int32_t, int); int  (*set_dup_compare)  (DB *, int (*)(DB *, const DBT *, const DBT *)); int  (*set_encrypt) (DB *, const char *, u_int32_t); void (*set_errcall) (DB *, void (*)(const DB_ENV *, const char *, const char *)); void (*set_errfile) (DB *, FILE *); void (*set_errpfx) (DB *, const char *); int  (*set_feedback) (DB *, void (*)(DB *, int, int)); int  (*set_flags) (DB *, u_int32_t); int  (*set_h_ffactor) (DB *, u_int32_t); int  (*set_h_hash)  (DB *, u_int32_t (*)(DB *, const void *, u_int32_t)); int  (*set_h_nelem) (DB *, u_int32_t); int  (*set_lorder) (DB *, int); void (*set_msgcall) (DB *, void (*)(const DB_ENV *, const char *)); void (*set_msgfile) (DB *, FILE *); int  (*set_pagesize) (DB *, u_int32_t); int  (*set_paniccall) (DB *, void (*)(DB_ENV *, int)); int  (*set_q_extentsize) (DB *, u_int32_t); int  (*set_re_delim) (DB *, int); int  (*set_re_len) (DB *, u_int32_t); int  (*set_re_pad) (DB *, int); int  (*set_re_source) (DB *, const char *); int  (*stat) (DB *, DB_TXN *, void *, u_int32_t); int  (*stat_print) (DB *, u_int32_t); int  (*sync) (DB *, u_int32_t); int  (*truncate) (DB *, DB_TXN *, u_int32_t *, u_int32_t); int  (*upgrade) (DB *, const char *, u_int32_t); int  (*verify)  (DB *, const char *, const char *, FILE *, u_int32_t); 
	int  (*dump) (DB *, const char *, int (*)(void *, const void *), void *, int, int); int  (*db_am_remove) (DB *, DB_TXN *, const char *, const char *); int  (*db_am_rename) (DB *, DB_TXN *, const char *, const char *, const char *); 

	int  (*stored_get) (DB *, DB_TXN *, DBT *, DBT *, u_int32_t); int  (*stored_close) (DB *, u_int32_t);
	u_int32_t am_ok;  

	int  preserve_fid;  
	u_int32_t orig_flags;      u_int32_t flags;
};
struct __dbc { DB *dbp;    DB_TXN  *txn;   

struct {  DBC *tqe_next;  DBC **tqe_prev; } links;

DBT  *rskey;   DBT  *rkey;    DBT  *rdata;  
DBT   my_rskey;   DBT   my_rkey;   DBT   my_rdata;  
void  *lref;    u_int32_t locker;   DBT   lock_dbt;   DB_LOCK_ILOCK lock;   DB_LOCK   mylock;  
u_int   cl_id;  
DBTYPE   dbtype;  
DBC_INTERNAL *internal;  
int (*c_close) (DBC *); int (*c_count) (DBC *, db_recno_t *, u_int32_t); int (*c_del) (DBC *, u_int32_t); int (*c_dup) (DBC *, DBC **, u_int32_t); int (*c_get) (DBC *, DBT *, DBT *, u_int32_t); int (*c_pget) (DBC *, DBT *, DBT *, DBT *, u_int32_t); int (*c_put) (DBC *, DBT *, DBT *, u_int32_t); 
int (*c_am_bulk) (DBC *, DBT *, u_int32_t); int (*c_am_close) (DBC *, db_pgno_t, int *); int (*c_am_del) (DBC *); int (*c_am_destroy) (DBC *); int (*c_am_get) (DBC *, DBT *, DBT *, u_int32_t, db_pgno_t *); int (*c_am_put) (DBC *, DBT *, DBT *, u_int32_t, db_pgno_t *); int (*c_am_writelock) (DBC *); 
u_int32_t flags;
};
struct __key_range { double less; double equal; double greater;
};
struct __db_bt_stat { u_int32_t bt_magic;   u_int32_t bt_version;   u_int32_t bt_metaflags;   u_int32_t bt_nkeys;   u_int32_t bt_ndata;   u_int32_t bt_pagesize;   u_int32_t bt_minkey;   u_int32_t bt_re_len;   u_int32_t bt_re_pad;   u_int32_t bt_levels;   u_int32_t bt_int_pg;   u_int32_t bt_leaf_pg;   u_int32_t bt_dup_pg;   u_int32_t bt_over_pg;   u_int32_t bt_empty_pg;   u_int32_t bt_free;   u_int32_t bt_int_pgfree;  u_int32_t bt_leaf_pgfree;  u_int32_t bt_dup_pgfree;  u_int32_t bt_over_pgfree; 
};
struct __db_compact {  u_int32_t compact_fillpercent;  db_timeout_t compact_timeout;  u_int32_t compact_pages;    u_int32_t compact_pages_free;  u_int32_t compact_pages_examine;  u_int32_t compact_levels;   u_int32_t compact_deadlock;  db_pgno_t compact_pages_truncated;   db_pgno_t compact_truncate; 
};
struct __db_h_stat { u_int32_t hash_magic;   u_int32_t hash_version;   u_int32_t hash_metaflags;  u_int32_t hash_nkeys;   u_int32_t hash_ndata;   u_int32_t hash_pagesize;  u_int32_t hash_ffactor;   u_int32_t hash_buckets;   u_int32_t hash_free;   u_int32_t hash_bfree;   u_int32_t hash_bigpages;  u_int32_t hash_big_bfree;  u_int32_t hash_overflows;  u_int32_t hash_ovfl_free;  u_int32_t hash_dup;   u_int32_t hash_dup_free; 
};
struct __db_qam_stat { u_int32_t qs_magic;   u_int32_t qs_version;   u_int32_t qs_metaflags;   u_int32_t qs_nkeys;   u_int32_t qs_ndata;   u_int32_t qs_pagesize;   u_int32_t qs_extentsize;  u_int32_t qs_pages;   u_int32_t qs_re_len;   u_int32_t qs_re_pad;   u_int32_t qs_pgfree;   u_int32_t qs_first_recno;  u_int32_t qs_cur_recno;  
};
struct __db_env { 
	void (*db_errcall) (const DB_ENV *, const char *, const char *); FILE  *db_errfile;  const char *db_errpfx; 
	FILE  *db_msgfile;       void (*db_msgcall) (const DB_ENV *, const char *);
	void (*db_feedback) (DB_ENV *, int, int); void (*db_paniccall) (DB_ENV *, int);
	void *(*db_malloc) (size_t); void *(*db_realloc) (void *, size_t); void (*db_free) (void *);

	u_int32_t  verbose; 
	void  *app_private; 
	int (*app_dispatch)       (DB_ENV *, DBT *, DB_LSN *, db_recops);
	u_int32_t mutex_align;  u_int32_t mutex_cnt;  u_int32_t mutex_inc;  u_int32_t mutex_tas_spins;
	struct {  int   alloc_id;   u_int32_t flags;  } *mutex_iq;    u_int  mutex_iq_next;  u_int  mutex_iq_max; 
	u_int8_t *lk_conflicts;  int   lk_modes;  u_int32_t  lk_max;  u_int32_t  lk_max_lockers; u_int32_t  lk_max_objects; u_int32_t  lk_detect;  db_timeout_t  lk_timeout; 
	u_int32_t  lg_bsize;  u_int32_t  lg_size;  u_int32_t  lg_regionmax;  int   lg_filemode; 
	u_int32_t  mp_gbytes;  u_int32_t  mp_bytes;  u_int   mp_ncache;  size_t   mp_mmapsize;  int   mp_maxopenfd;  int   mp_maxwrite;  int        mp_maxwrite_sleep;
	int   rep_eid;  int  (*rep_send)        (DB_ENV *, const DBT *, const DBT *, const DB_LSN *, int, u_int32_t);
	u_int32_t  tx_max;  time_t   tx_timestamp;  db_timeout_t  tx_timeout; 
	u_int32_t thr_nbucket;  u_int32_t thr_max;  void  *thr_hashtab; 

	pid_t  pid_cache; 
	char  *db_home;  char  *db_abshome;  char  *db_log_dir;  char  *db_tmp_dir; 
	char        **db_data_dir;  int   data_cnt;  int   data_next; 
	int   db_mode;  int   dir_mode;  void  *env_lref;  u_int32_t  open_flags; 
	void  *reginfo;  DB_FH  *lockfhp; 
	DB_FH  *registry;  u_int32_t registry_off; 
	void        (*thread_id) (DB_ENV *, pid_t *, db_threadid_t *); int        (*is_alive) (DB_ENV *, pid_t, db_threadid_t); char        *(*thread_id_string)   (DB_ENV *, pid_t, db_threadid_t, char *);
	int       (**recover_dtab)        (DB_ENV *, DBT *, DB_LSN *, db_recops, void *); size_t   recover_dtab_size;     
	void  *cl_handle;  u_int   cl_id;  
	int   db_ref; 
	long   shm_key; 

	db_mutex_t mtx_dblist;   struct {  struct __db *lh_first; } dblist;

	struct {  struct __db_env *tqe_next;  struct __db_env **tqe_prev; } links; struct __xa_txn {   struct __db_txn *tqh_first;  struct __db_txn **tqh_last; } xa_txn; int   xa_rmid; 
	char  *passwd;  size_t   passwd_len; void  *crypto_handle;  db_mutex_t  mtx_mt;  int   mti;   u_long  *mt;  
	void  *api1_internal;  void  *api2_internal; 
	void *lg_handle;   void *lk_handle;   void *mp_handle;   void *mutex_handle;   void *rep_handle;   void *tx_handle;  
//	int  (*close) (DB_ENV *, u_int32_t); int  (*dbremove) (DB_ENV *, DB_TXN *, const char *, const char *, u_int32_t); int  (*dbrename) (DB_ENV *, DB_TXN *, const char *, const char *, const char *, u_int32_t); void (*err) (const DB_ENV *, int, const char *, ...); void (*errx) (const DB_ENV *, const char *, ...); int  (*failchk) (DB_ENV *, u_int32_t); int  (*fileid_reset) (DB_ENV *, const char *, u_int32_t); int  (*get_cachesize) (DB_ENV *, u_int32_t *, u_int32_t *, int *); int  (*get_data_dirs) (DB_ENV *, const char ***); int  (*get_encrypt_flags) (DB_ENV *, u_int32_t *); void (*get_errfile) (DB_ENV *, FILE **); void (*get_errpfx) (DB_ENV *, const char **); int  (*get_flags) (DB_ENV *, u_int32_t *); int  (*get_home) (DB_ENV *, const char **); int  (*get_lg_bsize) (DB_ENV *, u_int32_t *); int  (*get_lg_dir) (DB_ENV *, const char **); int  (*get_lg_filemode) (DB_ENV *, int *); int  (*get_lg_max) (DB_ENV *, u_int32_t *); int  (*get_lg_regionmax) (DB_ENV *, u_int32_t *); int  (*get_lk_conflicts) (DB_ENV *, const u_int8_t **, int *); int  (*get_lk_detect) (DB_ENV *, u_int32_t *); int  (*get_lk_max_lockers) (DB_ENV *, u_int32_t *); int  (*get_lk_max_locks) (DB_ENV *, u_int32_t *); int  (*get_lk_max_objects) (DB_ENV *, u_int32_t *); int  (*get_mp_max_openfd) (DB_ENV *, int *); int  (*get_mp_max_write) (DB_ENV *, int *, int *); int  (*get_mp_mmapsize) (DB_ENV *, size_t *); void (*get_msgfile) (DB_ENV *, FILE **); int  (*get_open_flags) (DB_ENV *, u_int32_t *); int  (*get_rep_limit) (DB_ENV *, u_int32_t *, u_int32_t *); int  (*get_shm_key) (DB_ENV *, long *); int  (*get_timeout) (DB_ENV *, db_timeout_t *, u_int32_t); int  (*get_tmp_dir) (DB_ENV *, const char **); int  (*get_tx_max) (DB_ENV *, u_int32_t *); int  (*get_tx_timestamp) (DB_ENV *, time_t *); int  (*get_verbose) (DB_ENV *, u_int32_t, int *); int  (*is_bigendian) (void); int  (*lock_detect) (DB_ENV *, u_int32_t, u_int32_t, int *); int  (*lock_get) (DB_ENV *, u_int32_t, u_int32_t, const DBT *, db_lockmode_t, DB_LOCK *); int  (*lock_id) (DB_ENV *, u_int32_t *); int 
	//	(*lock_id_free) (DB_ENV *, u_int32_t); int  (*lock_put) (DB_ENV *, DB_LOCK *); int  (*lock_stat) (DB_ENV *, DB_LOCK_STAT **, u_int32_t); int  (*lock_stat_print) (DB_ENV *, u_int32_t); int  (*lock_vec) (DB_ENV *, u_int32_t, u_int32_t, DB_LOCKREQ *, int, DB_LOCKREQ **); int  (*log_archive) (DB_ENV *, char **[], u_int32_t); int  (*log_cursor) (DB_ENV *, DB_LOGC **, u_int32_t); int  (*log_file) (DB_ENV *, const DB_LSN *, char *, size_t); int  (*log_flush) (DB_ENV *, const DB_LSN *); int  (*log_printf) (DB_ENV *, DB_TXN *, const char *, ...); int  (*log_put) (DB_ENV *, DB_LSN *, const DBT *, u_int32_t); int  (*log_stat) (DB_ENV *, DB_LOG_STAT **, u_int32_t); int  (*log_stat_print) (DB_ENV *, u_int32_t); int  (*lsn_reset) (DB_ENV *, const char *, u_int32_t); int  (*memp_fcreate) (DB_ENV *, DB_MPOOLFILE **, u_int32_t); int  (*memp_register) (DB_ENV *, int, int (*)(DB_ENV *, db_pgno_t, void *, DBT *), int (*)(DB_ENV *, db_pgno_t, void *, DBT *)); int  (*memp_stat) (DB_ENV *, DB_MPOOL_STAT **, DB_MPOOL_FSTAT ***, u_int32_t); int  (*memp_stat_print) (DB_ENV *, u_int32_t); int  (*memp_sync) (DB_ENV *, DB_LSN *); int  (*memp_trickle) (DB_ENV *, int, int *); int  (*mutex_alloc) (DB_ENV *, u_int32_t, db_mutex_t *); int  (*mutex_free) (DB_ENV *, db_mutex_t); int  (*mutex_get_align) (DB_ENV *, u_int32_t *); int  (*mutex_get_increment) (DB_ENV *, u_int32_t *); int  (*mutex_get_max) (DB_ENV *, u_int32_t *); int  (*mutex_get_tas_spins) (DB_ENV *, u_int32_t *); int  (*mutex_lock) (DB_ENV *, db_mutex_t); int  (*mutex_set_align) (DB_ENV *, u_int32_t); int  (*mutex_set_increment) (DB_ENV *, u_int32_t); int  (*mutex_set_max) (DB_ENV *, u_int32_t); int  (*mutex_set_tas_spins) (DB_ENV *, u_int32_t); int  (*mutex_stat) (DB_ENV *, DB_MUTEX_STAT **, u_int32_t); int  (*mutex_stat_print) (DB_ENV *, u_int32_t); int  (*mutex_unlock) (DB_ENV *, db_mutex_t); int  (*open) (DB_ENV *, const char *, u_int32_t, int); int  (*remove) (DB_ENV *, const char *, u_int32_t); int  (*rep_elect)  (DB_ENV *, int, int, int, u_int32_t, int *, u_int32_t); int  (*re
	//	p_flush) (DB_ENV *); int  (*rep_get_config) (DB_ENV *, u_int32_t, int *); int  (*rep_process_message)  (DB_ENV *, DBT *, DBT *, int *, DB_LSN *); int  (*rep_set_config) (DB_ENV *, u_int32_t, int); int  (*rep_start) (DB_ENV *, DBT *, u_int32_t); int  (*rep_stat) (DB_ENV *, DB_REP_STAT **, u_int32_t); int  (*rep_stat_print) (DB_ENV *, u_int32_t); int  (*rep_sync) (DB_ENV *, u_int32_t); int  (*set_alloc) (DB_ENV *, void *(*)(size_t), void *(*)(void *, size_t), void (*)(void *)); int  (*set_app_dispatch)  (DB_ENV *, int (*)(DB_ENV *, DBT *, DB_LSN *, db_recops)); int  (*set_cachesize) (DB_ENV *, u_int32_t, u_int32_t, int); int  (*set_data_dir) (DB_ENV *, const char *); int  (*set_encrypt) (DB_ENV *, const char *, u_int32_t); void (*set_errcall) (DB_ENV *, void (*)(const DB_ENV *, const char *, const char *)); void (*set_errfile) (DB_ENV *, FILE *); void (*set_errpfx) (DB_ENV *, const char *); int  (*set_feedback) (DB_ENV *, void (*)(DB_ENV *, int, int)); int  (*set_flags) (DB_ENV *, u_int32_t, int); int  (*set_intermediate_dir) (DB_ENV *, int, u_int32_t); int  (*set_isalive) (DB_ENV *, int (*)(DB_ENV *, pid_t, db_threadid_t)); int  (*set_lg_bsize) (DB_ENV *, u_int32_t); int  (*set_lg_dir) (DB_ENV *, const char *); int  (*set_lg_filemode) (DB_ENV *, int); int  (*set_lg_max) (DB_ENV *, u_int32_t); int  (*set_lg_regionmax) (DB_ENV *, u_int32_t); int  (*set_lk_conflicts) (DB_ENV *, u_int8_t *, int); int  (*set_lk_detect) (DB_ENV *, u_int32_t); int  (*set_lk_max) (DB_ENV *, u_int32_t); int  (*set_lk_max_lockers) (DB_ENV *, u_int32_t); int  (*set_lk_max_locks) (DB_ENV *, u_int32_t); int  (*set_lk_max_objects) (DB_ENV *, u_int32_t); int  (*set_mp_max_openfd) (DB_ENV *, int); int  (*set_mp_max_write) (DB_ENV *, int, int); int  (*set_mp_mmapsize) (DB_ENV *, size_t); void (*set_msgcall)  (DB_ENV *, void (*)(const DB_ENV *, const char *)); void (*set_msgfile) (DB_ENV *, FILE *); int  (*set_paniccall) (DB_ENV *, void (*)(DB_ENV *, int)); int  (*set_rep_limit) (DB_ENV *, u_int32_t, u_int32_t); int  (*set_rep_request) (DB_ENV *, u
	//	_int32_t, u_int32_t); int  (*set_rep_transport) (DB_ENV *, int, int (*)(DB_ENV *, const DBT *, const DBT *, const DB_LSN *, int, u_int32_t)); int  (*set_rpc_server)  (DB_ENV *, void *, const char *, long, long, u_int32_t); int  (*set_shm_key) (DB_ENV *, long); int  (*set_thread_count) (DB_ENV *, u_int32_t); int  (*set_thread_id) (DB_ENV *, void (*)(DB_ENV *, pid_t *, db_threadid_t *)); int  (*set_thread_id_string) (DB_ENV *, char *(*)(DB_ENV *, pid_t, db_threadid_t, char *)); int  (*set_timeout) (DB_ENV *, db_timeout_t, u_int32_t); int  (*set_tmp_dir) (DB_ENV *, const char *); int  (*set_tx_max) (DB_ENV *, u_int32_t); int  (*set_tx_timestamp) (DB_ENV *, time_t *); int  (*set_verbose) (DB_ENV *, u_int32_t, int); int  (*stat_print) (DB_ENV *, u_int32_t); int  (*txn_begin) (DB_ENV *, DB_TXN *, DB_TXN **, u_int32_t); int  (*txn_checkpoint) (DB_ENV *, u_int32_t, u_int32_t, u_int32_t); int  (*txn_recover)  (DB_ENV *, DB_PREPLIST *, long, long *, u_int32_t); int  (*txn_stat) (DB_ENV *, DB_TXN_STAT **, u_int32_t); int  (*txn_stat_print) (DB_ENV *, u_int32_t); 
	int  (*prdbt) (DBT *, int, const char *, void *, int (*)(void *, const void *), int); 
	int   test_abort;  int   test_check;  int   test_copy; 
	u_int32_t flags;
};
#line 2291 "..\\build_win32\\db.h"
#line 2370 "..\\build_win32\\db.h"
#line 2374 "..\\build_win32\\db.h"
#line 2375 "..\\build_win32\\db.h"
#line 2383 "..\\build_win32\\db.h"
int db_create (DB **, DB_ENV *, u_int32_t);
char *db_strerror (int);
int db_env_create (DB_ENV **, u_int32_t);
char *db_version (int *, int *, int *);
int log_compare (const DB_LSN *, const DB_LSN *);
int db_env_set_func_close (int (*)(int));
int db_env_set_func_dirfree (void (*)(char **, int));
int db_env_set_func_dirlist (int (*)(const char *, char ***, int *));
int db_env_set_func_exists (int (*)(const char *, int *));
int db_env_set_func_free (void (*)(void *));
int db_env_set_func_fsync (int (*)(int));
int db_env_set_func_ftruncate (int (*)(int, off_t));
int db_env_set_func_ioinfo (int (*)(const char *, int, u_int32_t *, u_int32_t *, u_int32_t *));
//int db_env_set_func_malloc (void *(*)(size_t));
int db_env_set_func_map (int (*)(char *, size_t, int, int, void **));
int db_env_set_func_pread (ssize_t (*)(int, void *, size_t, off_t));
int db_env_set_func_pwrite (ssize_t (*)(int, const void *, size_t, off_t));
int db_env_set_func_open (int (*)(const char *, int, ...));
int db_env_set_func_read (ssize_t (*)(int, void *, size_t));
//int db_env_set_func_realloc (void *(*)(void *, size_t));
int db_env_set_func_rename (int (*)(const char *, const char *));
int db_env_set_func_seek (int (*)(int, off_t, int));
int db_env_set_func_sleep (int (*)(u_long, u_long));
int db_env_set_func_unlink (int (*)(const char *));
int db_env_set_func_unmap (int (*)(void *, size_t));
int db_env_set_func_write (ssize_t (*)(int, const void *, size_t));
int db_env_set_func_yield (int (*)(void));
int db_sequence_create (DB_SEQUENCE **, DB *, u_int32_t);
#line 2433 "..\\build_win32\\db.h"
#line 2438 "..\\build_win32\\db.h"
#line 2442 "..\\build_win32\\db.h"
#line 2443 "..\\build_win32\\db.h"
#line 15 ".\\bench.c"
struct db_time { u_int32_t secs, usecs;
};
struct db_time  start_time, end_time;
u_int32_t  pagesize = 32 * 1024;
u_int   bulkbufsize = 4 * 1024 * 1024;
u_int            logbufsize = 8 * 1024 * 1024;
u_int            cachesize = 32 * 1024 * 1024;
u_int   datasize = 32;
u_int    keysize = 8;
u_int            numitems = 0;
FILE             *fp;
char  *progname;
extern void __os_clock(DB_ENV *, u_int32_t *, u_int32_t *);
void cleanup(void);
void op_ds(u_int, int);
void op_ds_bulk(u_int, u_int *);
void op_tds(u_int, int, u_int32_t);
void res(char *, u_int);
void usage(void);
void
res(char *msg, u_int ops)
{ // struct db_time v; char buf[1000]; unsigned i; double elapsed;
//v.secs = end_time.secs - start_time.secs; v.usecs = end_time.usecs - start_time.usecs; if (start_time.usecs > end_time.usecs) {  v.secs--;  v.usecs += 1000000; } elapsed = v.secs + v.usecs / 1e6;
sprintf(buf,"%s:\t%f s : %.5f mio key/data pairs  / s\n",  msg, elapsed, ops / elapsed / 1000000); for (i = 0; i < strlen(buf) ; i++)  if (buf[i]=='.')   buf[i]=',';
printf("%s",buf); 
}
void
op_ds(u_int ops, int update)
{// DB *dbp; DBT key, data; DB_ENV *dbenv;  char *databuf, *keybuf;
cleanup();
keybuf = malloc(keysize); ((keybuf != ((void *)0)) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 89, "keybuf != NULL"), abort()));
(((databuf = malloc(datasize)) != ((void *)0)) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 91, "(databuf = malloc(datasize)) != NULL"), abort())); memset(&key, 0, sizeof(key)); memset(&data, 0, sizeof(data)); key.data = keybuf; key.size = keysize; memset(keybuf, 'a', keysize);
data.data = databuf; data.size = datasize; memset(databuf, 'b', datasize);
((db_create(&dbp, ((void *)0), 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 102, "db_create(&dbp, NULL, 0) == 0"), abort()));
dbp->set_errfile(dbp, (&_iob[2]));
#line 113 ".\\bench.c"
((dbp->set_pagesize(dbp, pagesize) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 114, "dbp->set_pagesize(dbp, pagesize) == 0"), abort())); ((dbp->open(dbp, ((void *)0), ((void *)0), ((void *)0), DB_BTREE, 0x0000001, 0666) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 115, "dbp->open(dbp, NULL, NULL, NULL, DB_TYPE, DB_CREATE, 0666) == 0"), abort()));
dbenv = dbp->dbenv; 
if (update) {           __os_clock(((void *)0), &start_time.secs, &start_time.usecs);  for (; ops > 0; --ops) {   keybuf[(ops % keysize)] =       "abcdefghijklmnopqrstuvwxyz"[(ops % 26)];   ((dbp->put(dbp, ((void *)0), &key, &data, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 129, "dbp->put(dbp, NULL, &key, &data, 0) == 0"), abort()));  }  __os_clock(((void *)0), &end_time.secs, &end_time.usecs); } else  {  ((dbp->put(dbp, ((void *)0), &key, &data, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 134, "dbp->put(dbp, NULL, &key, &data, 0) == 0"), abort()));  __os_clock(((void *)0), &start_time.secs, &start_time.usecs);  for (; ops > 0; --ops)   ((dbp->get(dbp, ((void *)0), &key, &data, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 137, "dbp->get(dbp, NULL, &key, &data, 0) == 0"), abort()));  __os_clock(((void *)0), &end_time.secs, &end_time.usecs); }

((dbp->close(dbp, 23) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 144, "dbp->close(dbp, DB_NOSYNC) == 0"), abort())); free(keybuf); free(databuf);
}
void
op_ds_bulk(u_int ops, u_int *totalp)
{ //DB *dbp; DBC *dbc; DBT key, data; DB_ENV *dbenv;  u_int32_t len, klen; u_int i, total; char *keybuf, *databuf; void *pointer, *dp, *kp;
cleanup();

(((keybuf = malloc(keysize+4)) != ((void *)0)) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 168, "(keybuf = malloc(keysize+4)) != NULL"), abort())); (((databuf = malloc(bulkbufsize)) != ((void *)0)) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 169, "(databuf = malloc(bulkbufsize)) != NULL"), abort()));
memset(&key, 0, sizeof(key)); memset(&data, 0, sizeof(data)); key.data = keybuf; key.size = keysize;
data.data = databuf; data.size = datasize; memset(databuf, 'b', datasize);
((db_create(&dbp, ((void *)0), 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 180, "db_create(&dbp, NULL, 0) == 0"), abort()));
dbp->set_errfile(dbp, (&_iob[2]));
((dbp->set_pagesize(dbp, pagesize) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 184, "dbp->set_pagesize(dbp, pagesize) == 0"), abort())); ((dbp->set_cachesize(dbp, 0, cachesize, 1) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 185, "dbp->set_cachesize(dbp, 0, cachesize, 1) == 0"), abort())); ((dbp->open(dbp, ((void *)0), ((void *)0), ((void *)0), DB_BTREE, 0x0000001, 0666) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 187, "dbp->open(dbp, NULL, NULL, NULL, DB_BTREE, DB_CREATE, 0666) == 0"), abort()));
for (i = 1; i <= numitems; ++i) {  (void)sprintf(keybuf, "%10d", i);  ((dbp->put(dbp, ((void *)0), &key, &data, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 191, "dbp->put(dbp, NULL, &key, &data, 0) == 0"), abort())); }
#line 200 ".\\bench.c"
((dbp->cursor(dbp, ((void *)0), &dbc, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 201, "dbp->cursor(dbp, NULL, &dbc, 0) == 0"), abort()));
data.ulen = bulkbufsize; data.flags = 0x020;
dbenv = dbp->dbenv; 
if (ops > 10000)  ops = 10000;
__os_clock(((void *)0), &start_time.secs, &start_time.usecs); for (total = 0; ops > 0; --ops) {  ((dbc->c_get( dbc, &key, &data, 9 | 0x10000000) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 215, "dbc->c_get( dbc, &key, &data, DB_FIRST | DB_MULTIPLE_KEY) == 0"), abort()));  (pointer = (u_int8_t *)(&data)->data + (&data)->ulen - sizeof(u_int32_t));  while (pointer != ((void *)0)) {   do { if (*((u_int32_t *)(pointer)) == (u_int32_t)-1) { dp = ((void *)0); kp = ((void *)0); pointer = ((void *)0); break; } kp = (u_int8_t *) (&data)->data + *(u_int32_t *)(pointer); (pointer) = (u_int32_t *)(pointer) - 1; klen = *(u_int32_t *)(pointer); (pointer) = (u_int32_t *)(pointer) - 1; dp = (u_int8_t *) (&data)->data + *(u_int32_t *)(pointer); (pointer) = (u_int32_t *)(pointer) - 1; len = *(u_int32_t *)(pointer); (pointer) = (u_int32_t *)(pointer) - 1; } while (0);   if (kp != ((void *)0))    ++total;  } } __os_clock(((void *)0), &end_time.secs, &end_time.usecs); *totalp = total;

#line 235 ".\\bench.c"
((dbp->close(dbp, 23) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 236, "dbp->close(dbp, DB_NOSYNC) == 0"), abort())); free(keybuf); free(databuf);
}
void
op_tds(u_int ops, int update, u_int32_t txn_flags)
{ //DB *dbp; DBT key, data; DB_ENV *dbenv;  DB_TXN *txn; char *keybuf, *databuf;
cleanup();
(((keybuf = malloc(keysize)) != ((void *)0)) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 253, "(keybuf = malloc(keysize)) != NULL"), abort())); (((databuf = malloc(datasize)) != ((void *)0)) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 254, "(databuf = malloc(datasize)) != NULL"), abort()));
memset(&key, 0, sizeof(key)); memset(&data, 0, sizeof(data)); key.data = keybuf; key.size = keysize; memset(keybuf, 'a', keysize);
data.data = databuf; data.size = datasize; memset(databuf, 'b', datasize);
((db_env_create(&dbenv, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 266, "db_env_create(&dbenv, 0) == 0"), abort()));
dbenv->set_errfile(dbenv, (&_iob[2]));
((dbenv->set_flags(dbenv, 0x01000000 | txn_flags, 1) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 271, "dbenv->set_flags(dbenv, DB_AUTO_COMMIT | txn_flags, 1) == 0"), abort())); ((dbenv->set_lg_bsize(dbenv, logbufsize) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 272, "dbenv->set_lg_bsize(dbenv, logbufsize) == 0"), abort())); ((dbenv->open(dbenv, "TESTDIR", 0x0000001 | 0x0100000 | 0x0004000 | 0x0008000 | 0x0010000 | 0x0040000, 0666) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 275, "dbenv->open(dbenv, PERF_TESTDIR, DB_CREATE | DB_PRIVATE | DB_INIT_LOCK | DB_INIT_LOG | DB_INIT_MPOOL | DB_INIT_TXN, 0666) == 0"), abort()));
((db_create(&dbp, dbenv, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 277, "db_create(&dbp, dbenv, 0) == 0"), abort())); ((dbp->set_pagesize(dbp, pagesize) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 278, "dbp->set_pagesize(dbp, pagesize) == 0"), abort())); ((dbp->open(dbp, ((void *)0), "a", ((void *)0), DB_BTREE, 0x0000001, 0666) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 280, "dbp->open(dbp, NULL, PERF_DB, NULL, DB_BTREE, DB_CREATE, 0666) == 0"), abort()));
if (update) {  
	__os_clock(((void *)0), &start_time.secs, &start_time.usecs);  for (; ops > 0; --ops)   ((dbp->put(dbp, ((void *)0), &key, &data, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 287, "dbp->put(dbp, NULL, &key, &data, 0) == 0"), abort()));  __os_clock(((void *)0), &end_time.secs, &end_time.usecs);
} else {  ((dbp->put(dbp, ((void *)0), &key, &data, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 293, "dbp->put(dbp, NULL, &key, &data, 0) == 0"), abort()));  
__os_clock(((void *)0), &start_time.secs, &start_time.usecs);  for (; ops > 0; --ops) {   ((dbenv->txn_begin(dbenv, ((void *)0), &txn, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 299, "dbenv->txn_begin(dbenv, NULL, &txn, 0) == 0"), abort()));   ((dbp->get(dbp, ((void *)0), &key, &data, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 300, "dbp->get(dbp, NULL, &key, &data, 0) == 0"), abort()));   ((txn->commit(txn, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 301, "txn->commit(txn, 0) == 0"), abort()));  }  __os_clock(((void *)0), &end_time.secs, &end_time.usecs);
}
((dbp->close(dbp, 23) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 309, "dbp->close(dbp, DB_NOSYNC) == 0"), abort())); ((dbenv->close(dbenv, 0) == 0) ? (void)0 : (fprintf((&_iob[2]), "PERF_CHECK failure: %s:%d: \"%s\"", ".\\bench.c", 310, "dbenv->close(dbenv, 0) == 0"), abort())); free(keybuf); free(databuf);
}
void
cleanup()
{
	_unlink("TESTDIR/a"); _unlink("a"); _rmdir("TESTDIR"); _mkdir("TESTDIR");
#line 326 ".\\bench.c"
}
int
main(int argc, char *argv[])
{ /*extern char *optarg; extern int optind; u_int ops, total; int ch, major, minor, patch; int i;
if ((progname = strrchr(argv[0], '/')) == ((void *)0))  progname = argv[0]; else  ++progname;
ops = 10000000;
while ((ch = getopt(argc, argv, "d:k:o:p:")) != (-1))  switch (ch) {  case 'd':   datasize = (u_int)atoi(optarg);   break;  case 'k':   keysize = (u_int)atoi(optarg);   break;  case 'o':   ops = (u_int)atoi(optarg);   break;  case 'p':   pagesize = (u_int32_t)atoi(optarg);   break;  case '?':  default:   usage();  } argc -= optind; argv += optind;
numitems = (cachesize / (keysize + datasize - 1)) / 2;
(void)db_version(&major, &minor, &patch); printf("Using Berkeley DB %d.%d.%d - ", major, minor, patch); printf("ops: %u; keysize: %d; datasize: %d\n", ops, keysize, datasize);
for (i = 0; i < 500 ; i++) {  op_ds(ops, 0);  res("Data Store (read):", ops); }
return 0;
if (keysize >= 8) {  op_ds_bulk(ops, &total);  res("Data Store (bulk read):", total); } else {  printf("Data Store (bulk read):\n");  printf("\tskipped: bulk get requires a key size >= 10\n"); }
op_ds(ops, 1);
res("Data Store (write):", ops);
op_tds(ops, 0, 0); res("Transactional Data Store (read):", ops);
op_tds(ops, 1, 0x00080000); res("Transactional Data Store (write, in-memory logging):", ops);
op_tds(ops, 1, 0x0000100); res("Transactional Data Store (write, no-sync on commit):", ops);
op_tds(ops, 1, 0x0000400); res("Transactional Data Store (write, write-no-sync on commit):", ops);
op_tds(ops, 1, 0); res("Transactional Data Store (write, sync on commit):", ops);
return (0);*/
}
void
usage()
{ fprintf((&_iob[2]),     "usage: %s [-d datasize] [-k keysize] [-o ops] [-p pagesize]\n",     progname); exit(1);
}
