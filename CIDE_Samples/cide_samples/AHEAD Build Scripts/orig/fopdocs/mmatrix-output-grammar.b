TOKEN : { 
  < INTEGER: ("-")? ["1"-"9"] (["0"-"9"])* >
}

Root : (Layer)+ ;

Layer : "layer" IDENTIFIER [FileList] :: layerdecl
      ;

FileList : (File)+ ;

File : "file" QualName [EntryList] :: filedecl
     ;

EntryList : (Entry)+ ;

Entry : IDENTIFIER IDENTIFIER IDENTIFIER [Type] LnRange [IDList] "endMods" 
        [Keys] [Nest] :: Entrie

       /* type     decl       name  [optional-type]   LneRange  modifiers */
      ;

LnRange : "Line" "range" "(" INTEGER "," INTEGER ")" :: LineRange
         ;

IDList : (IdName)+ ;


IdName: IDENTIFIER :: Name ;

Type : [Func] ParamType :: TypeDecl ;

Func : "(" [ParamList] ")" :: FuncDecl ;

ParamList : ParamType ("," ParamType)* ;

ParamType: QualName [ArrayList] :: ParamDecl ;

ArrayList : (Array)+ ;

Array : "[]" :: ArrDecl ;

Keys : "beginKeys" [NamedVectorList] "endKeys" ::Keyes ;

NamedVectorList : (NVList)+ ;

NVList : IDENTIFIER ":(" QNList "):" :: Nvl ;

Nest : "beginNest" [EntryList] "endNest" ::Nested ;

QualName : IdName ( "." IdName)* ;

QNList : (QualName)+ ;
